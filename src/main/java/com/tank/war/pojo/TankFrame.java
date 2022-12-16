package com.tank.war.pojo;

import com.tank.war.enums.Direction;
import com.tank.war.enums.Group;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 11:04
 * @Description:
 * 1.在内部类Builder中创建坦克对象TankFrame时监听键盘事件
 * 2.根据键盘按下的左、上、右、下分别设置对应的标志
 * 3.根据设置的标志，标记坦克移动的方向
 * 4.根据坦克移动的方向，将坦克向对应的方向进行移动
 */
public class TankFrame extends Frame {

    public static final ThreadLocal<Graphics> GRAPHICS_LOCAL = new ThreadLocal<>();

    private Integer width;

    private Integer height;

    private static Tank tank;

    private List<Bullet> bullets = new ArrayList<>();
    //敌军坦克
    private static List<Tank> enemyTanks = new ArrayList<>();

    private TankFrame() {
    }

    private TankFrame(Builder builder) {
        this.width = builder.width;
        this.height = builder.height;
        tank = builder.tank;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public List<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    public static class Builder{
        private Integer width = 800;
        private Integer height = 600;
        private Tank tank;

        public Builder setWidth(Integer width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(Integer height) {
            this.height = height;
            return this;
        }

        public Builder setTank(Tank tank){
            this.tank = tank;
            return this;
        }

        public TankFrame build(){
            TankFrame frame = new TankFrame(this);
            frame.setSize(width,height);
            frame.setResizable(false);
            frame.setTitle("tank war");
            frame.setVisible(true);
            //监听窗口事件
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            //1.监听键盘事件
            frame.addKeyListener(new MyKeyListener());
            return frame;
        }
    }

    /**
     * 双缓冲解决坦克窗口屏幕闪烁问题
     */
    Image offScreenImg = null;
    @Override
    public void update(Graphics g){
        if (offScreenImg == null){
            //在内存中创建一个与坦克窗口大小一致的画板
            offScreenImg = this.createImage(width,height);
        }
        Graphics offScreenGraphics = offScreenImg.getGraphics();
        Color color = offScreenGraphics.getColor();
        offScreenGraphics.setColor(Color.white);
        offScreenGraphics.fillRect(0,0,width,height);
        offScreenGraphics.setColor(color);
        paint(offScreenGraphics);
        //将内存中的画板复制到坦克窗口中
        g.drawImage(offScreenImg,0,0,null);
    }

    /**
     * 该方法是系统自动调用，相当于在画布上作画，让坦克移动和发射子弹
     */
    @Override
    public void paint(Graphics g){
        GRAPHICS_LOCAL.set(g);
        //4.根据坦克移动的方向，将坦克向对应的方向进行移动
        tank.paint(g);
        g.drawString("子弹的数量：" + bullets.size(),20,50);
        //由于增强for循环是迭代器中提供的，如果使用增强for，在删除bullets中的数据时会报并发修改异常。
        //或者使用迭代器删除也可以。也就是说使用迭代器遍历list时，不能使用list自带的remove方法删除元素。
        /*for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }*/
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()){
            Bullet b = it.next();
            b.paint(g);
            if (!b.isLive()){
                it.remove();
            }
        }
        //画出敌军坦克
        for (Tank enemy : enemyTanks){
            enemy.paint(g);
        }
        //互相消灭对方坦克
        eliminate();
    }

    private void eliminate() {
        for (int i = 0; i < bullets.size(); i++) {
            //己方消灭敌方坦克
            for (int j = 0; j < enemyTanks.size(); j++) {
                collideWith(bullets.get(i),enemyTanks.get(j));
                if (bullets.size() == 0 || enemyTanks.size() == 0){
                    break;
                }
            }
            //敌方消灭我方坦克
            if (bullets.size() != 0 && tank.isLiving()){
                collideWith(bullets.get(i),tank);
            }
        }
    }

    /**
     * 如果己方子弹与对方的坦克碰撞，则己方子弹与对方坦克都消失
     * @param bullet 子弹
     * @param tank 坦克
     */
    private void collideWith(Bullet bullet, Tank tank) {
        //防止坦克的子弹打到自己或队友
        if (bullet.getGroup().equals(tank.getGroup())){
            return;
        }
        //在创建坦克或子弹时就创建对应的Rectangle，不用每次循环都创建Rectangle，减少内存浪费
        Rectangle b = bullet.getRectangle();
        Rectangle t = tank.getRectangle();
        if (b.intersects(t)){
            bullet.die();
            tank.die();
            bullets.remove(bullet);
            enemyTanks.remove(tank);
            //爆炸
            Explode e = new Explode(tank.getX(),tank.getY());
            for (int i = 0; i < ResourceMgr.explodes.length; i++) {
                e.paint(GRAPHICS_LOCAL.get());
            }
            //爆炸时产生声音
            new Thread(ResourceMgr.explodeAudio).start();
            GRAPHICS_LOCAL.remove();
        }
    }

    static class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            //2.根据键盘按下的左、上、右、下分别设置对应的标志
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            //3.根据设置的标志，标记坦克移动的方向
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    //释放Ctrl键，我军坦克发射子弹
                    tank.fire();
                    break;
                /*case KeyEvent.VK_SHIFT:
                    //释放shift键，敌军坦克发射子弹
                    for (Tank enemy : enemyTanks){
                        enemy.fire(Group.BAD);
                    }
                    break;*/
                default:
                    break;
            }

            setMainTankDir();
        }

        public void setMainTankDir(){
            if (bL || bU || bR || bD){
                tank.setMoving(true);
                if (bL){
                    tank.setDirection(Direction.LEFT);
                }
                if (bU){
                    tank.setDirection(Direction.UP);
                }
                if (bR){
                    tank.setDirection(Direction.RIGHT);
                }
                if (bD){
                    tank.setDirection(Direction.DOWN);
                }
            } else {
                tank.setMoving(false);
            }
        }
    }

}
