package com.tank.war.pojo;

import com.tank.war.enums.Direction;
import com.tank.war.enums.Group;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 18:17
 * @Description:
 */
public class Tank extends TankBulletObj {

    private TankFrame tankFrame;

    public Tank() {
    }

    public Tank(Integer x, Integer y, Direction direction, Group group) {
        super(x,y,ResourceMgr.tankL.getWidth(),ResourceMgr.tankL.getHeight(),direction,5,group);
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    @Override
    public void paint(Graphics g){
        //如果坦克不存活，则不画出坦克
        if (!isLiving()){
            return;
        }
        //g.fillRect(getX(),getY(),getWidth(),getHeight());
        Integer x = getX();
        Integer y = getY();
        BufferedImage img;
        if (this.getGroup().equals(Group.GOOD)){
            img = getImg(ResourceMgr.tankL,ResourceMgr.tankU,ResourceMgr.tankR,ResourceMgr.tankD);
        } else {
            img = getImg(ResourceMgr.enemyTankL,ResourceMgr.enemyTankU,ResourceMgr.enemyTankR,ResourceMgr.enemyTankD);
        }
        g.drawImage(img,x,y,null);
        //让敌方坦克随机移动并发射子弹
        if (this.getGroup().equals(Group.BAD)){
            Random random = new Random();
            int i = random.nextInt(100);
            if (i >= 95){
                setMoving(true);
                fire(this.getGroup());
                //随机改变坦克的方向
                setDirection(Direction.values()[random.nextInt(4)]);
            }
        }
        if (isMoving()){
            move();
        }
        boundCheck();
    }

    @Override
    public void fire(Group group){
        int x = getX() + getWidth()/2;
        int y = getY() + getHeight()/2;
        Bullet bullet = new Bullet(x, y, getDirection(),group);
        bullet.setX(x-bullet.getWidth()/2);
        bullet.setY(y-bullet.getHeight()/2);
        bullet.setTankFrame(tankFrame);
        this.tankFrame.getBullets().add(bullet);
    }

    /**
     * 边界检测，防止坦克跑出边界
     */
    private void boundCheck(){
        int x = getX();
        int y = getY();
        Integer width = getWidth();
        Integer height = getHeight();
        if (x < 0){
            setX(0);
        }else if (x > this.tankFrame.getWidth() - width){
            setX(this.tankFrame.getWidth() - width);
        }
        if (y < 30){
            //窗口上面有个30px的边框
            setY(30);
        } else if (y > this.tankFrame.getHeight() - height){
            setY(this.tankFrame.getHeight() - height);
        }
    }

}
