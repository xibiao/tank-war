package com.tank.war.pojo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/14 11:04
 * @Description:
 */
public class TankFrame extends Frame {

    private Integer width;

    private Integer height;

    private Integer x = 200;

    private Integer y = 200;

    private TankFrame() {
    }

    private TankFrame(Builder builder) {
        this.width = builder.width;
        this.height = builder.height;
    }

    public static class Builder{
        private Integer width = 800;
        private Integer height = 600;

        public Builder setWidth(Integer width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(Integer height) {
            this.height = height;
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
            //监听键盘事件
            frame.addKeyListener(new MyKeyListener());
            return frame;
        }
    }

    @Override
    public void paint(Graphics g){
        g.fillRect(x,y,50,50);
        //x += 5;
        System.out.println("paint");
    }

    static class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
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
                default:
                    break;
            }
        }
    }

}
