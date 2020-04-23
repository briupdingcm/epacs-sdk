package com.epacs.sdk.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 操作配置文件的工具类
 *
 * @author Kevin
 * @version 1.1
 */
public class PropUtils{
    private static Properties prop;

    static {
        try{
            PropUtils.prop=new Properties();
            PropUtils.prop.load(PropUtils.class.getResourceAsStream("/epac-client.properties"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static int getInt(String key){
        return Integer.parseInt(PropUtils.prop.getProperty(key));
    }

    public static String getString(String key){
        return PropUtils.prop.getProperty(key);
    }
}
