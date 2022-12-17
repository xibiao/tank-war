package com.tank.war.responsibilityChain;

import com.tank.war.pojo.GameObject;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 23:09
 * @Description: 碰撞器，模拟责任链模式中的抽象接口
 */
public interface Collider {

    boolean collide(GameObject o1, GameObject o2);

}
