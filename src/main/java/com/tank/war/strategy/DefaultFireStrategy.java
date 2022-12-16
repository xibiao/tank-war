package com.tank.war.strategy;

import com.tank.war.pojo.Bullet;
import com.tank.war.pojo.Tank;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 9:05
 * @Description:
 */
public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        createBullet(tank, tank.getDirection());
    }
}
