package com.tank.war.strategy;

import com.tank.war.enums.Direction;
import com.tank.war.pojo.Bullet;
import com.tank.war.pojo.Tank;
import com.tank.war.utils.DirectionUtil;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 9:08
 * @Description:
 */
public class ThreeDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        Direction currDir = tank.getDirection();
        Direction leftDir = DirectionUtil.getLeftDir(currDir);
        Direction rightDir = DirectionUtil.getRightDir(currDir);
        for (Direction dir : Direction.values()){
            //左右前开炮
            if (dir.equals(leftDir) || dir.equals(rightDir) || dir.equals(currDir)){
                createBullet(tank, dir);
            }
        }
    }
}
