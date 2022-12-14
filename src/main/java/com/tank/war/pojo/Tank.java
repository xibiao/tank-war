package com.tank.war.pojo;

import com.tank.war.enums.Direction;

import java.awt.*;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 18:17
 * @Description:
 */
public class Tank {
    private static final int SPEED = 10;

    private Integer x = 200;

    private Integer y = 200;

    private Direction direction = Direction.RIGHT;

    private boolean moving = false;

    public Tank() {
    }

    public Tank(Integer x, Integer y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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

    public void paint(Graphics g){
        g.fillRect(x,y,50,50);
        if (moving){
            move();
        }
    }

    private void move(){
        //根据坦克移动的方向，将坦克向对应的方向进行移动
        switch (direction){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
    }
}
