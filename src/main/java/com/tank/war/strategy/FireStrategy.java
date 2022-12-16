package com.tank.war.strategy;

import com.tank.war.pojo.Tank;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 9:04
 * @Description: 开火策略
 * 可以模拟坦克吃下不同的能量块具有不同的开火策略，
 * 将能量块定义成一个类Energy，在坦克类中用List<Energy>容器装有吃下的能量块，
 * 根据不同数量的能量块发出不同数量或不同方向的子弹
 */
public interface FireStrategy {

    void fire(Tank tank);

}
