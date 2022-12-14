package com.tank.war.pojo;

import com.tank.war.enums.Direction;

import java.awt.*;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 18:17
 * @Description:
 */
public class Tank extends TankBulletObj {

    public Tank() {
    }

    public Tank(Integer x, Integer y, Direction direction) {
        super(x,y,50,50,direction,5);
    }

    @Override
    public void paint(Graphics g){
        g.fillRect(getX(),getY(),getWidth(),getHeight());
        if (isMoving()){
            move();
        }
    }

}
