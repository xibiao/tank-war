package com.tank.war.utils;

import com.tank.war.enums.Direction;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 11:17
 * @Description:
 */
public class DirectionUtil {

    /**
     * 根据坦克的当前方向获取左方向
     * @param currDir 坦克的当前方向
     */
    public static Direction getLeftDir(Direction currDir){
        if (currDir.equals(Direction.LEFT)){
            return Direction.DOWN;
        } else if (currDir.equals(Direction.UP)){
            return Direction.LEFT;
        } else if ((currDir.equals(Direction.RIGHT))){
            return Direction.UP;
        }else {
            return Direction.RIGHT;
        }
    }

    /**
     * 根据坦克的当前方向获取右方向
     * @param currDir 坦克的当前方向
     */
    public static Direction getRightDir(Direction currDir){
        if (currDir.equals(Direction.LEFT)){
            return Direction.UP;
        } else if (currDir.equals(Direction.UP)){
            return Direction.RIGHT;
        } else if ((currDir.equals(Direction.RIGHT))){
            return Direction.DOWN;
        }else {
            return Direction.LEFT;
        }
    }

    /**
     * 根据坦克的当前方向获取后方向
     * @param currDir 坦克的当前方向
     */
    public static Direction getRearDir(Direction currDir){
        if (currDir.equals(Direction.LEFT)){
            return Direction.RIGHT;
        } else if (currDir.equals(Direction.UP)){
            return Direction.DOWN;
        } else if ((currDir.equals(Direction.RIGHT))){
            return Direction.LEFT;
        }else {
            return Direction.UP;
        }
    }

}
