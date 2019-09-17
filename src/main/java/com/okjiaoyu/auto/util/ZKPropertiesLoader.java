package com.okjiaoyu.auto.util;

import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigProfile;
import lombok.extern.slf4j.Slf4j;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by dell on 2015/12/14.
 */
@Slf4j
public class ZKPropertiesLoader {

    private static String path = System.getProperty("user.dir") + "/conf/zk.properties";

    /***
     * 系统 提前 加载配置文件 到系统变量里面
     *
     * @param rootNode
     */
    public static void load(String rootNode) throws IOException {

        System.setProperty("projectName", "qa-webplatform");

        Properties properties = new Properties();
        ZookeeperConfigGroup group2 = null;
        try {
            InputStream e = ZKPropertiesLoader.class.getClassLoader().getResourceAsStream("zk.properties");
            if (e != null) {
                properties.load(e);
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
                properties.load(bufferedReader);
            }
            String zkAddress = properties.getProperty("zk.address");
            String zkVersion = properties.getProperty("zk.version");
            ZookeeperConfigProfile zookeeperConfigProfile = new ZookeeperConfigProfile(zkAddress, rootNode, zkVersion);
            group2 = new ZookeeperConfigGroup(zookeeperConfigProfile, "unchange");
            for (Map.Entry<String, String> entry : group2.exportProperties().entrySet()) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        } catch (Exception var2) {
            log.error("zkCLient: 未找到zk.properties配置文件", var2);
            throw var2;
        } finally {
            if (group2 != null) {
                group2.close();
            }
        }

    }
}
