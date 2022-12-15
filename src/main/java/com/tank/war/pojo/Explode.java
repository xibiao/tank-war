package com.tank.war.pojo;

import com.tank.war.enums.Direction;
import com.tank.war.enums.Group;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/15 16:13
 * @Description:
 */
public class Explode extends TankBulletObj {

    private BufferedImage[] images = ResourceMgr.explodes;

    private int step = 0;

    public Explode() {}

    public Explode(Integer x, Integer y) {
        super(x, y, 0, 0, null, 0, null);
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(images[step++],getX(),getY(),null);
        if (step >= images.length){
            step = 0;
        }
    }

}
