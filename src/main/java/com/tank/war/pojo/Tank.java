package com.tank.war.pojo;

import com.tank.war.common.ResourceMgr;
import com.tank.war.enums.Direction;
import com.tank.war.enums.Group;
import com.tank.war.strategy.*;
import com.tank.war.utils.DirectionUtil;

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

    private FireContext context;

    private Set<Energy> energySet = new HashSet<>();

    public Tank() {
    }

    public Tank(Integer x, Integer y, Direction direction, Group group, FireContext context) {
        super(x,y, ResourceMgr.tankL.getWidth(),ResourceMgr.tankL.getHeight(),direction,5,group);
        this.context = context;
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
            if (i >= 97){
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
     * 如果坦克到达边界，则立即掉头
     */
    private void boundCheck(){
        if (!isExceedBound()){
            return;
        }
        int x = getX();
        int y = getY();
        Integer width = getWidth();
        Integer height = getHeight();
        if (x < 0){
            setX(0);
        }else if (x > TankFrame.width - width){
            setX(TankFrame.width - width);
        }
        if (y < 30){
            //窗口上面有个30px的边框
            setY(30);
        } else if (y > TankFrame.height - height){
            setY(TankFrame.height - height);
        }
        //如果坦克到达边界，则立即掉头
        DirectionUtil.reverseMove(this);
    }

    /**
     * 判断是否越界
     */
    public boolean isExceedBound(){
        boolean f1 = getX() < 0;
        boolean f2 = getX() > TankFrame.width - getWidth();
        boolean f3 = getY() < 30;
        boolean f4 = getY() > TankFrame.height - getHeight();
        return f1 || f2 || f3 || f4;
    }

    public void eatEnergy(List<Energy> energyList){
        //不让敌方坦克吃能量块
        if (this.getGroup().equals(Group.BAD)){
            return;
        }
        for (Energy energy : energyList){
            Rectangle t = this.getRectangle();
            Rectangle e = energy.getRectangle();
            if (t.intersects(e)){
                energySet.add(energy);
                //坦克吃掉能量块之后，能量块就要消失
                energy.setLiving(false);
            }
        }
    }

    /**
     * 判断两只坦克是否碰撞
     */
    public boolean isCollide(Tank tank){
        Rectangle r1 = this.getRectangle();
        Rectangle r2 = tank.getRectangle();
        return r1.intersects(r2);
    }

}
