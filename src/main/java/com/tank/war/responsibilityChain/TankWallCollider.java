package com.tank.war.responsibilityChain;

import com.tank.war.pojo.GameObject;
import com.tank.war.pojo.Tank;
import com.tank.war.pojo.Wall;
import com.tank.war.utils.DirectionUtil;

import java.awt.*;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/17 20:25
 * @Description:
 */
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall){
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;
            collideWith(t,w);
            return false;
        } else if (o1 instanceof Wall && o2 instanceof Tank){
            collide(o2,o1);
        }
        return true;
    }

    /**
     * 如果坦克与墙相撞，则坦克掉头返回
     */
    private void collideWith(Tank tank, Wall wall){
        Rectangle r1 = tank.getRectangle();
        Rectangle r2 = wall.getRectangle();
        if (r1.intersects(r2)){
            DirectionUtil.reverseMove(tank);
        }
    }
}
