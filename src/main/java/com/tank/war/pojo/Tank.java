package com.tank.war.pojo;

import com.tank.war.enums.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 18:17
 * @Description:
 */
public class Tank extends TankBulletObj {

    private TankFrame tankFrame;

    private boolean enemy = false;

    public Tank() {
    }

    public Tank(Integer x, Integer y, Direction direction) {
        super(x,y,ResourceMgr.tankL.getWidth(),ResourceMgr.tankL.getHeight(),direction,5);
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setEnemy(boolean enemy) {
        this.enemy = enemy;
    }

    @Override
    public void paint(Graphics g){
        //g.fillRect(getX(),getY(),getWidth(),getHeight());
        Integer x = getX();
        Integer y = getY();
        BufferedImage img;
        if (!enemy){
            img = getImg(ResourceMgr.tankL,ResourceMgr.tankU,ResourceMgr.tankR,ResourceMgr.tankD);
        } else {
            img = getImg(ResourceMgr.enemyTankL,ResourceMgr.enemyTankU,ResourceMgr.enemyTankR,ResourceMgr.enemyTankD);
        }
        g.drawImage(img,x,y,null);
        if (isMoving()){
            move();
        }
    }

    private BufferedImage getImg(BufferedImage l, BufferedImage u, BufferedImage r, BufferedImage d){
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

    @Override
    public void fire(){
        int x = getX() + getWidth()/2;
        int y = getY() + getHeight()/2;
        Bullet bullet = new Bullet(x, y, getDirection());
        bullet.setX(x-bullet.getWidth()/2);
        bullet.setY(y-bullet.getHeight()/2);
        bullet.setTankFrame(tankFrame);
        this.tankFrame.getBullets().add(bullet);
    }

}
