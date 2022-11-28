package com.tian.springcloud.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: tian
 * @Date: 2020/2/24 1:46
 * @Desc:  eurekaServer在运行期间会去统计心跳失败比例在15分钟之内是否低于85%，
 *         如果低于85%，eurekaServer会将这些实例保护起来，让这些
 *         实例不会过期，但是在保护期内如果服务刚好这个服务者非正常下线了，
 *         此时服务消费者就会拿到一个无效的服务实例，此时会调用失败，对于这个问题
 *         需要服务消费者端有一些容错机制，如重试、断路器等。
 *         我们再单击测试的时候很容易满足心跳失败比例在15分钟之内低于85%，
 *         这个时候就会触发eureka的保护机制，
 *         一旦开启了保护机制，则服务注册中心维护的服务实例就不是那么准确了，
 *         此时我们可以使用eureka.server.enable.-self-presvation=false来关闭保护机制，
 *         这样可以确保注册中心可不用的实例
 */
@SpringBootApplication
@EnableEurekaServer //开启eureka服务端
public class EurekaServerApplication {
    
    private static AtomicInteger nextIndex = new AtomicInteger();

    public static void main(String[] args) {
        int current = nextIndex.get();
        int next = (current + 1) % 3;
        
        if (nextIndex.compareAndSet(current, next)) {
            System.out.println("1111");
        }
        
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
