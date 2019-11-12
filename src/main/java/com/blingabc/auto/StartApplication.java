package com.blingabc.auto;

import com.blingabc.auto.util.ZKPropertiesLoader;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-17:15:58
 * Modify date: 2019-09-17:15:58
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.blingabc.auto.dao")
@PropertySource(value = {"classpath:data.properties"})
public class StartApplication implements CommandLineRunner,WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Value("${server.port}")
    private String serverPort;

    public static void main(String[] args) {
//        try {
//            ZKPropertiesLoader.load("/xdfapp/qa-webplatform");
//        } catch (IOException e) {
//            e.printStackTrace();;
//        }
        SpringApplication.run(StartApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(Integer.parseInt(serverPort));
    }

}
