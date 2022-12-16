package com.tank.war.strategy;

import com.tank.war.enums.Direction;
import com.tank.war.pojo.Bullet;
import com.tank.war.pojo.Tank;
import com.tank.war.utils.DirectionUtil;
import com.tank.war.utils.ImageUtil;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 9:08
 * @Description:
 */
public class TwoDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int x = tank.getX() + tank.getWidth()/2;
        int y = tank.getY() + tank.getHeight()/2;
        Direction direction = tank.getDirection();
        Direction leftDir = DirectionUtil.getLeftDir(direction);
        Direction rightDir = DirectionUtil.getRightDir(direction);
        for (Direction dir : Direction.values()){
            //左右开炮
            if (dir.equals(leftDir) || dir.equals(rightDir)){
                Bullet bullet = new Bullet(x, y, dir,tank.getGroup());
                bullet.setX(x-bullet.getWidth()/2);
                bullet.setY(y-bullet.getHeight()/2);
                bullet.setTankFrame(tank.getTankFrame());
                tank.getTankFrame().getBullets().add(bullet);
            }
        }
    }
}
