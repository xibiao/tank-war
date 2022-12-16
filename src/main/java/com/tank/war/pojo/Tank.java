package com.tank.war.pojo;

import com.tank.war.common.ResourceMgr;
import com.tank.war.enums.Direction;
import com.tank.war.enums.Group;
import com.tank.war.strategy.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 18:17
 * @Description:
 */
public class Tank extends TankBulletObj {

    private TankFrame tankFrame;

    private FireContext context;

    private Set<Energy> energySet = new HashSet<>();

    public Tank() {
    }

    public Tank(Integer x, Integer y, Direction direction, Group group, FireContext context) {
        super(x,y, ResourceMgr.tankL.getWidth(),ResourceMgr.tankL.getHeight(),direction,5,group);
        this.context = context;
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
                fire();
                //随机改变坦克的方向
                setDirection(Direction.values()[random.nextInt(4)]);
            }
        }
        if (isMoving()){
            move();
        }
        boundCheck();
        //吃掉能量块的数量
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString("吃掉能量块的数量==" + energySet.size(),50,50);
        g.setColor(color);
    }

    @Override
    public void fire(){
        FireStrategy strategy = new DefaultFireStrategy();
        if (this.getGroup().equals(Group.GOOD)){
            if (energySet.size() == 1){
                strategy = new TwoDirFireStrategy();
            } else if (energySet.size() == 2){
                strategy = new ThreeDirFireStrategy();
            } else if (energySet.size() >= 3){
                strategy = new FourDirFireStrategy();
            }
        }
        context.fire(this,strategy);
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

    public void eatEnergy(){
        //不让敌方坦克吃能量块
        if (this.getGroup().equals(Group.BAD)){
            return;
        }
        for (Energy energy : Energy.getEnergyList()){
            Rectangle t = this.getRectangle();
            Rectangle e = energy.getRectangle();
            if (t.intersects(e)){
                energySet.add(energy);
                //坦克吃掉能量块之后，能量块就要消失
                energy.setLiving(false);
            }
        }
    }

}
