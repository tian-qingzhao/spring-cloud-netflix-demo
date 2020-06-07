package com.tian.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import javax.annotation.PostConstruct;

/**
 * @Author: tian
 * @Date: 2020/6/6 0:41
 * @Desc:
 */
@SpringBootApplication
@EnableConfigServer //开启springcloud-config服务端
public class ConfigServerAppliction {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerAppliction.class,args);
    }
}
