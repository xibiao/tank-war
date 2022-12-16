package com.tank.war.pojo;

import com.tank.war.enums.Direction;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    public static Integer width;

    public static Integer height;

    private static GameModel gameModel;

    private TankFrame() {
    }

    private TankFrame(Builder builder) {
        width = builder.width;
        height = builder.height;
        gameModel = builder.gameModel;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public static class Builder{
        private Integer width = 800;
        private Integer height = 600;
        private GameModel gameModel;

        public Builder setWidth(Integer width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(Integer height) {
            this.height = height;
            return this;
        }

        public Builder setGameModel(GameModel gameModel){
            this.gameModel = gameModel;
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
        offScreenGraphics.setColor(Color.BLACK);
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
        gameModel.paint(g);
    }

    static class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        private Tank tank = gameModel.getTank();

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
