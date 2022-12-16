package com.tank.war.pojo;

import com.tank.war.common.ResourceMgr;

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

    private Tank tank;

    private List<Bullet> bullets = new ArrayList<>();
    //敌军坦克
    private static List<Tank> enemyTanks = new ArrayList<>();

    public GameModel() {
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    public void paint(Graphics g){
        GRAPHICS_LOCAL.set(g);
        //4.根据坦克移动的方向，将坦克向对应的方向进行移动
        tank.paint(g);
        //g.drawString("子弹的数量：" + bullets.size(),20,50);
        //由于增强for循环是迭代器中提供的，如果使用增强for，在删除bullets中的数据时会报并发修改异常。
        //或者使用迭代器删除也可以。也就是说使用迭代器遍历list时，不能使用list自带的remove方法删除元素。
        /*for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }*/
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()){
            Bullet b = it.next();
            b.paint(g);
            if (!b.isLive()){
                it.remove();
            }
        }
        //画出敌军坦克
        for (Tank enemy : enemyTanks){
            enemy.paint(g);
        }
        //互相消灭对方坦克
        eliminate();
        //生成能量块，并让坦克去吃能量块
        createAndEatEnergy();


        //最后从ThreadLocal中删除
        GRAPHICS_LOCAL.remove();
    }

    private void eliminate() {
        for (int i = 0; i < bullets.size(); i++) {
            //己方消灭敌方坦克
            for (int j = 0; j < enemyTanks.size(); j++) {
                collideWith(bullets.get(i),enemyTanks.get(j));
                if (bullets.size() == 0 || enemyTanks.size() == 0){
                    break;
                }
            }
            //敌方消灭我方坦克
            /*if (bullets.size() != 0 && tank.isLiving()){
                collideWith(bullets.get(i),tank);
            }*/
        }
    }

    /**
     * 如果己方子弹与对方的坦克碰撞，则己方子弹与对方坦克都消失
     * @param bullet 子弹
     * @param tank 坦克
     */
    private void collideWith(Bullet bullet, Tank tank) {
        //防止坦克的子弹打到自己或队友
        if (bullet.getGroup().equals(tank.getGroup())){
            return;
        }
        //在创建坦克或子弹时就创建对应的Rectangle，不用每次循环都创建Rectangle，减少内存浪费
        Rectangle b = bullet.getRectangle();
        Rectangle t = tank.getRectangle();
        if (b.intersects(t)){
            bullet.die();
            tank.die();
            bullets.remove(bullet);
            enemyTanks.remove(tank);
            //爆炸
            Explode e = new Explode(tank.getX(),tank.getY());
            for (int i = 0; i < ResourceMgr.explodes.length; i++) {
                e.paint(GRAPHICS_LOCAL.get());
            }
            //爆炸时产生声音
            new Thread(ResourceMgr.explodeAudio).start();
        }
    }

    private void createAndEatEnergy(){
        List<Energy> energyList = Energy.createEnergy();
        for (Energy e : energyList){
            e.paint(GRAPHICS_LOCAL.get());
        }
        tank.eatEnergy();
    }

}
