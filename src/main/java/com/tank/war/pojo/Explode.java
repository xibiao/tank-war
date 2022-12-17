package com.tank.war.pojo;

import com.tank.war.common.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/15 16:13
 * @Description:
 */
public class Explode extends GameObject {

    private BufferedImage[] images = ResourceMgr.explodes;

    private int step = 0;

    public Explode() {}

    public Explode(Integer x, Integer y) {
        super(x, y, 0, 0);
        GameModel.getInstance().add(this);
        //爆炸时产生声音
        new Thread(ResourceMgr.explodeAudio).start();
    }

    @Override
    public void paint(Graphics g){
        for (int i = 0; i < ResourceMgr.explodes.length; i++){
            g.drawImage(images[step++],getX(),getY(),null);
            if (step >= images.length){
                //爆炸结束之后，设置爆炸图片不存活，以便删除爆炸图片，不在界面上画出
                //GameModel.getInstance().remove(this);
                setLiving(false);
            }
        }
    }

}
