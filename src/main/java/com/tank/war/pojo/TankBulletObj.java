package com.tank.war.pojo;

import com.tank.war.enums.Direction;
import com.tank.war.enums.Group;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 18:48
 * @Description:
 */
public abstract class TankBulletObj extends AbstractThing {

    private Direction direction = Direction.RIGHT;
    //移动速度
    private Integer speed = 5;

    private boolean moving = false;

    private Group group = Group.BAD;

    public TankBulletObj() {
    }

    public TankBulletObj(Integer x, Integer y, Integer width, Integer height,
                         Direction direction, Integer speed, Group group) {
        super(x,y,width,height);
        this.direction = direction;
        this.speed = speed;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    void paint(Graphics g){
        g.fillRect(getX(),getY(),getWidth(),getHeight());
        if (moving){
            move();
        }
    }

    void move(){
        int x = getX();
        int y = getY();
        //根据坦克移动的方向，将坦克向对应的方向进行移动
        switch (direction){
            case LEFT:
                x -= speed;
                setX(x);
                break;
            case UP:
                y -= speed;
                setY(y);
                break;
            case RIGHT:
                x += speed;
                setX(x);
                break;
            case DOWN:
                y += speed;
                setY(y);
                break;
            default:
                break;
        }
        //坦克或子弹移动了，需要修改其对应的rectangle
        getRectangle().x = x;
        getRectangle().y = y;
    }

    void fire(){}

    void die(){
        setLiving(false);
    }

    BufferedImage getImg(BufferedImage l, BufferedImage u, BufferedImage r, BufferedImage d){
        Direction dir = getDirection();
        BufferedImage img = null;
        switch (dir){
            case LEFT:
                img = l;
                setWidth(img.getWidth());
                setHeight(img.getHeight());
                break;
            case UP:
                img = u;
                setWidth(img.getWidth());
                setHeight(img.getHeight());
                break;
            case RIGHT:
                img = r;
                setWidth(img.getWidth());
                setHeight(img.getHeight());
                break;
            case DOWN:
                img = d;
                setWidth(img.getWidth());
                setHeight(img.getHeight());
                break;
            default:
                break;
        }
        return img;
    }

}
