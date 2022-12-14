package com.tank.war.pojo;

import com.tank.war.enums.Direction;

import java.awt.*;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 18:53
 * @Description:
 */
public class Bullet extends TankBulletObj {

    public Bullet() {
    }

    public Bullet(Integer x, Integer y, Direction direction) {
        super(x, y, 5, 5, direction, 10);
    }

    @Override
    void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(getX(),getY(),getWidth(),getHeight());
        g.setColor(c);

        move();
    }

}
