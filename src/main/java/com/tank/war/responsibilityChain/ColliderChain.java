package com.tank.war.responsibilityChain;

import com.tank.war.pojo.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/17 8:30
 * @Description: 碰撞器链，模拟责任链模式
 */
public class ColliderChain {

    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain add(Collider collider){
        this.colliders.add(collider);
        return this;
    }

    public void doCollide(GameObject o1, GameObject o2){
        for (Collider c : colliders){
            if (!c.collide(o1,o2)){
                break;
            }
        }
    }

}
