package com.tank.war.pojo;

import com.tank.war.common.ResourceMgr;
import com.tank.war.enums.Direction;
import com.tank.war.enums.Group;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    public Bullet(Integer x, Integer y, Direction direction, Group group) {
        super(x, y, 10, 10, direction, 10,group);
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    @Override
    void paint(Graphics g) {
        //如果子弹不存活，则不画出子弹
        if (!isLiving()){
            return;
        }
        //如果子弹超出弹窗，则删除子弹，防止产生过多子弹对象导致内存溢出
        /*if (!live){
            this.tankFrame.getBullets().remove(this);
        }*/
        /*Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(getX(),getY(),getWidth(),getHeight());
        g.setColor(c);*/

        BufferedImage img = getImg(ResourceMgr.bulletL,ResourceMgr.bulletU,ResourceMgr.bulletR,ResourceMgr.bulletD);
        g.drawImage(img,getX(),getY(),null);

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
