package com.tank.war.pojo;

import java.awt.*;
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
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            return frame;
        }
    }

}
