package com.tank.war.strategy;

import com.tank.war.enums.Direction;
import com.tank.war.pojo.Bullet;
import com.tank.war.pojo.Tank;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 9:08
 * @Description:
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int x = tank.getX() + tank.getWidth()/2;
        int y = tank.getY() + tank.getHeight()/2;
        for (Direction dir : Direction.values()){
            Bullet bullet = new Bullet(x, y, dir,tank.getGroup());
            bullet.setX(x-bullet.getWidth()/2);
            bullet.setY(y-bullet.getHeight()/2);
            bullet.setTankFrame(tank.getTankFrame());
            tank.getTankFrame().getBullets().add(bullet);
        }
    }
}
