package com.tank.war.pojo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/15 11:06
 * @Description:
 */
public class ResourceMgr {

    public static BufferedImage tankL,tankU,tankR,tankD;
    public static BufferedImage enemyTankL,enemyTankU,enemyTankR,enemyTankD;
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            //我方坦克
            tankL = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tank/tankL.png")));
            tankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tank/tankU.png")));
            tankR = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tank/tankR.png")));
            tankD = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tank/tankD.png")));
            //敌方坦克
            enemyTankL = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tank/enemyTankL.png")));
            enemyTankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tank/enemyTankU.png")));
            enemyTankR = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tank/enemyTankR.png")));
            enemyTankD = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tank/enemyTankD.png")));
            //子弹
            bulletL = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bullet/bulletL.gif")));
            bulletU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bullet/bulletU.gif")));
            bulletR = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bullet/bulletR.gif")));
            bulletD = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bullet/bulletD.gif")));
            //爆炸
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/explode/e" + (i+1) +".gif")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
