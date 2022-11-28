package com.tian.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @Author: tian
 * @Date: 2020/6/6 0:41
 * @Desc: 配置中心服务启动类。
 * 客户端集成配置中心需要添加 `spring-boot-starter-actuator` 依赖，并在需要动态刷新的类上添加 {@link RefreshScope} 注解，
 * 访问 ip:port/actuator/refresh 接口，完成配置的动态刷新。
 * 原理为 `RefreshEndpoint` 进行的刷新入口。
 *
 * 当有多个微服务多个实例的时候，每个都实例都需要访问一遍 actuator/refresh 接口完成配置的动态刷新，比较麻烦。
 * 可集成spring-cloud-bus消息总线，
 * 具体使用是在需要动态刷新配置的服务里面添加 `spring-cloud-starter-bus-amqp` 依赖，并安装rabbitmq服务端，
 * 在git更改完配置之后，同时需要通过postman发起调用  配置中心客户端的服务ip:port/actuator/bus-refresh 进行通过。
 * 原理为git修改完之后会推送给客户端，被请求的客户端会发消息给mq，mq收到消息之后同步给其他服务或其他实例。
 */
@SpringBootApplication
@EnableConfigServer //开启springcloud-config服务端
public class ConfigServerAppliction {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerAppliction.class,args);
    }
}
