package com.tank.war.utils;

import com.tank.war.enums.Direction;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/16 11:17
 * @Description:
 */
public class DirectionUtil {

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
