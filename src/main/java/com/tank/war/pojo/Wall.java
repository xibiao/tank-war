package com.tank.war.pojo;

import java.awt.*;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/17 19:47
 * @Description:
 */
public class Wall extends GameObject {

    public Wall() {
    }

    public Wall(Integer x, Integer y, Integer width, Integer height) {
        super(x, y, width, height);
        GameModel.getInstance().add(this);
    }

    @Override
    void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.PINK);
        g.drawRect(getX(),getY(),getWidth(),getHeight());
        g.setColor(color);
    }
}
