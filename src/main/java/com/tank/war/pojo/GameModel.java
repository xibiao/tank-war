package com.tank.war.pojo;

import com.tank.war.responsibilityChain.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 20:26
 * @Description: 数据模型，只与数据(坦克、子弹等)打交道，将数据模型与视图展示分离
 */
public class GameModel {

    public static final ThreadLocal<Graphics> GRAPHICS_LOCAL = new ThreadLocal<>();
    //我军坦克，主坦克
    private Tank tank;

    private List<GameObject> gameObjects = new ArrayList<>();

    private ColliderChain chain = new ColliderChain();

    private GameModel() {
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public void add(GameObject gameObject){
        this.gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject){
        this.gameObjects.remove(gameObject);
    }

    public ColliderChain getChain() {
        return chain;
    }

    public void setChain(ColliderChain chain) {
        this.chain = chain;
    }

    public void paint(Graphics g){
        GRAPHICS_LOCAL.set(g);
        //4.根据坦克移动的方向，将坦克向对应的方向进行移动
        tank.paint(g);
        //g.drawString("子弹的数量：" + bullets.size(),20,50);
        //由于增强for循环是迭代器中提供的，如果使用增强for，在删除bullets中的数据时会报并发修改异常。
        //或者使用迭代器删除也可以。也就是说使用迭代器遍历list时，不能使用list自带的remove方法删除元素。
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject obj = gameObjects.get(i);
            //从gameObjects列表中取出所有的物体在Frame画板上画出来
            obj.paint(g);
            //如果子弹超出弹窗，或者子弹与坦克碰撞，说明子弹与坦克不活着了，
            //则删除子弹或坦克，防止产生过多子弹对象导致内存溢出
            if (!obj.isLiving()){
                remove(obj);
            }
        }

        //互相消灭对方坦克
        eliminate();
        //生成能量块，并让坦克去吃能量块
        createAndEatEnergy();


        //最后从ThreadLocal中删除
        GRAPHICS_LOCAL.remove();
    }

    private void eliminate() {
        for (int i = 0; i < gameObjects.size()-1; i++) {
            GameObject obj = gameObjects.get(i);
            for (int j = i+1; j < gameObjects.size(); j++) {
                chain.doCollide(obj,gameObjects.get(j),chain);
            }
            //敌方消灭我方坦克
            /*if (tank.isLiving()){
                chain.doCollide(obj,tank,chain);
            }*/
        }
    }

    private void createAndEatEnergy(){
        List<Energy> energyList = Energy.createEnergy();
        tank.eatEnergy(energyList);
    }

    /**
     * 单例模式，全局只创建一个GameModel实例
     */
    private static class GameModelInner{
        private static final GameModel INSTANCE = new GameModel();
    }

    public static GameModel getInstance(){
        return GameModelInner.INSTANCE;
    }

}
