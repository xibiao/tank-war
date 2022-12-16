package com.tank.war.pojo;

import com.tank.war.common.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/15 16:13
 * @Description:
 */
public class Explode extends AbstractThing {

    private BufferedImage[] images = ResourceMgr.explodes;

    private int step = 0;

    public Explode() {}

    public Explode(Integer x, Integer y) {
        super(x, y, 0, 0);
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(images[step++],getX(),getY(),null);
        if (step >= images.length){
            step = 0;
        }
    }

}
