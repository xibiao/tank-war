package com.tank.war.responsibilityChain;

import com.tank.war.pojo.Bullet;
import com.tank.war.pojo.GameObject;
import com.tank.war.pojo.Wall;

import java.awt.*;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/17 20:18
 * @Description:
 */
public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall){
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;
            collideWith(b,w);
            return false;
        } else if (o1 instanceof Wall && o2 instanceof Bullet){
            return collide(o2,o1);
        } else {
            return true;
        }
    }

    /**
     * 如果子弹与墙相撞，则子弹消失
     */
    private void collideWith(Bullet bullet, Wall wall){
        Rectangle r1 = bullet.getRectangle();
        Rectangle r2 = wall.getRectangle();
        if (r1.intersects(r2)){
            System.out.println("子弹与墙碰撞了");
            bullet.die();
        }
    }
}
