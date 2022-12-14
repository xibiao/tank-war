package com.tank.war.pojo;

import com.tank.war.enums.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 18:17
 * @Description:
 */
public class Tank extends TankBulletObj {

    private TankFrame tankFrame;

    public Tank() {
    }

    public Tank(Integer x, Integer y, Direction direction) {
        super(x,y,50,50,direction,5);
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    @Override
    public void paint(Graphics g){
        g.fillRect(getX(),getY(),getWidth(),getHeight());
        if (isMoving()){
            move();
        }
    }

    @Override
    public void fire(){
        Bullet bullet = new Bullet(getX(), getY(), getDirection());
        bullet.setTankFrame(tankFrame);
        this.tankFrame.getBullets().add(bullet);
    }

}
