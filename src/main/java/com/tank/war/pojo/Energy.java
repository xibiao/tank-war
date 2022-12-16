package com.tank.war.pojo;

import com.tank.war.common.ResourceMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 9:33
 * @Description:
 */
public class Energy extends AbstractThing {

    private static List<Energy> energyList = new ArrayList<>();

    private Energy() {
    }

    private Energy(Integer x, Integer y, Integer width, Integer height) {
        super(x, y, width, height);
    }

    public static List<Energy> getEnergyList() {
        return energyList;
    }

    @Override
    void paint(Graphics g) {
        //如果能量块被坦克吃掉，则不画出能量块
        if (!isLiving()){
            return;
        }
        Color color = g.getColor();
        g.setColor(Color.yellow);
        g.drawImage(ResourceMgr.energyImg,getX(),getY(),null);
        g.setColor(color);
    }

    /**
     * 随机生成能量块
     */
    public static List<Energy> createEnergy(){
        if (energyList.size() >= 3){
            return energyList;
        }
        Random random = new Random();
        int i = random.nextInt(1000);
        if (i > 99){
            int x = random.nextInt(500);
            int y = random.nextInt(500);
            energyList.add(new Energy(x,y,10,10));
        }
        return energyList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(),getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Energy)){
            return false;
        }
        Energy e = (Energy) obj;
        return getX().equals(e.getX()) && getY().equals(e.getY());
    }
}
