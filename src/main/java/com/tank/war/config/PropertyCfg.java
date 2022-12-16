package com.tank.war.config;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: Xibiao Cao
 * @Date: 2022/12/15 21:18
 * @Description:
 */
public class PropertyCfg {

    private static Properties prop = new Properties();

    private PropertyCfg(){}

    private static class Inner{
        private static final PropertyCfg PROPERTY_CFG = new PropertyCfg();
    }

    public static PropertyCfg getInstance(){
        return Inner.PROPERTY_CFG;
    }

    static {
        try {
            prop.load(PropertyCfg.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        return prop.getProperty(key);
    }

}
