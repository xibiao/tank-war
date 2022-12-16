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

    private boolean living = true;

    private Group group = Group.BAD;
    //创建坦克或子弹对应的Rectangle，需要根据Rectangle来判断坦克与子弹是否碰撞
    private Rectangle rectangle = new Rectangle();

    public TankBulletObj() {
    }

    public TankBulletObj(Integer x, Integer y, Integer width, Integer height,
                         Direction direction, Integer speed, Group group) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.speed = speed;
        this.group = group;
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

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
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
        //坦克或子弹移动了，需要修改其对应的rectangle
        rectangle.x = x;
        rectangle.y = y;
    }

    void fire(){}

    void die(){
        living = false;
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
