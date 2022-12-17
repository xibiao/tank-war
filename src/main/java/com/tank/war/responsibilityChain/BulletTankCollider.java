package com.tank.war.responsibilityChain;

import com.tank.war.common.ResourceMgr;
import com.tank.war.pojo.Bullet;
import com.tank.war.pojo.Explode;
import com.tank.war.pojo.GameObject;
import com.tank.war.pojo.Tank;

import java.awt.*;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 23:10
 * @Description: 子弹与坦克碰撞器，一个具体碰撞器类
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2, ColliderChain chain) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            collideWith(b,t);
            //如果子弹与坦克碰撞了，则结束碰撞链，不执行下一个碰撞器的collide方法
            return false;
        } else if (o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2,o1,chain);
        } else {
            //如果不是子弹与坦克碰撞，则继续执行下一个碰撞器的collide方法
            //chain.doCollide(o1,o2,chain);
            return true;
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
            //remove(bullet);
            //remove(tank);
            //创建爆炸图片，并添加到GameModel的gameObjects属性中
            new Explode(tank.getX(),tank.getY());
        }
    }

}
