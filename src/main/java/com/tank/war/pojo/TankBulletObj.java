package com.tank.war.pojo;

import com.tank.war.enums.Direction;

import java.awt.*;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 18:48
 * @Description:
 */
public abstract class TankBulletObj {
    //坐标位置
    private Integer x = 200;
    private Integer y = 200;
    //宽高大小
    private Integer width;
    private Integer height;

    private Direction direction = Direction.RIGHT;
    //移动速度
    private Integer speed = 5;

    private boolean moving = false;

    public TankBulletObj() {
    }

    public TankBulletObj(Integer x, Integer y, Integer width, Integer height, Direction direction, Integer speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.speed = speed;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    void paint(Graphics g){
        g.fillRect(x,y,width,height);
        if (moving){
            move();
        }
    }

    void move(){
        //根据坦克移动的方向，将坦克向对应的方向进行移动
        switch (direction){
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            default:
                break;
        }
    }

    void fire(){}

}
