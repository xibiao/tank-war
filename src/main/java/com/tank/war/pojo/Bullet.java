package com.tank.war.pojo;

import com.tank.war.enums.Direction;

import java.awt.*;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 18:53
 * @Description:
 */
public class Bullet extends TankBulletObj {

    private TankFrame tankFrame;

    private boolean live = true;

    public Bullet() {
    }

    public Bullet(Integer x, Integer y, Direction direction) {
        super(x, y, 10, 10, direction, 10);
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    @Override
    void paint(Graphics g) {
        if (!live){
            this.tankFrame.getBullets().remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(getX(),getY(),getWidth(),getHeight());
        g.setColor(c);

        move();
    }

    @Override
    void move(){
        super.move();
        Integer x = getX();
        Integer y = getY();
        int width = tankFrame.getWidth();
        int height = tankFrame.getHeight();
        if (x < 0 || x > width || y < 0 || y > height){
            live = false;
        }
    }

}
