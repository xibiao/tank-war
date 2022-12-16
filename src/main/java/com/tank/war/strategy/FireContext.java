package com.tank.war.strategy;

import com.tank.war.enums.Group;
import com.tank.war.pojo.Tank;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 9:14
 * @Description:
 */
public class FireContext {

    private FireStrategy strategy;

    public FireContext() {
    }

    public FireContext(FireStrategy strategy) {
        this.strategy = strategy;
    }

    public void fire(Tank tank){
        if (tank.getGroup().equals(Group.GOOD)){
            strategy = new FourDirFireStrategy();
        } else {
            strategy = new DefaultFireStrategy();
        }
        strategy.fire(tank);
    }

    public void fire(Tank tank, FireStrategy strategy){
        strategy.fire(tank);
    }

}
