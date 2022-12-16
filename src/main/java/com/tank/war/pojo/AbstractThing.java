package com.tank.war.pojo;

import java.awt.*;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 9:39
 * @Description:
 */
public abstract class AbstractThing {
    //坐标位置
    private Integer x = 200;
    private Integer y = 200;
    //宽高大小
    private Integer width;
    private Integer height;
    //是否存活
    private boolean living = true;
    //创建坦克或子弹对应的Rectangle，需要根据Rectangle来判断坦克与子弹是否碰撞
    private Rectangle rectangle = new Rectangle();

    public AbstractThing() {
    }

    public AbstractThing(Integer x, Integer y, Integer width, Integer height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(x,y,width,height);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    abstract void paint(Graphics g);

}
