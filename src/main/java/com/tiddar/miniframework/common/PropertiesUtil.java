package com.tiddar.miniframework.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件读取类
 *
 * @author tiddar
 */
public class PropertiesUtil {
    /**
     * 缓存配置文件内容，减少文件访问次数
     */
    private static Map<String, String> cache = new HashMap<String, String>();


    /**
     * 获取配置文件内的属性
     *
     * @return
     * @para propKey
     */
    public static String getProperties(String propKey) {
        if (cache.get(propKey) != null) {
            return cache.get(propKey);
        } else {
            try {
                InputStream in = Utility.class.getClassLoader().getResourceAsStream("mini.properties");
                Properties p = new Properties();
                p.load(in);
                in.close();
                String propValue = p.getProperty(propKey);
                cache.put(propKey,propValue);
                return propValue;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }

}
