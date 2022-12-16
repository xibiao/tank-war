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
        //四个方向同时开炮
        for (Direction dir : Direction.values()){
            createBullet(tank, dir);
        }
    }
}
