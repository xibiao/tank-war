package com.tank.war.responsibilityChain;

import com.tank.war.pojo.GameObject;
import com.tank.war.pojo.Tank;
import com.tank.war.utils.DirectionUtil;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/17 9:32
 * @Description: 坦克与坦克碰撞器，一个具体碰撞器类
 */
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2, ColliderChain chain) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (!t1.isCollide(t2)){
                return false;
            }
            if (t1.isExceedBound() && t2.isExceedBound()){
                return false;
            } else if (t1.isExceedBound()){
                DirectionUtil.reverseMove(t2);
            } else if (t2.isExceedBound()){
                DirectionUtil.reverseMove(t1);
            } else {
                DirectionUtil.reverseMove(t1);
                DirectionUtil.reverseMove(t2);
            }
            return false;
        }
        return true;
    }
}
