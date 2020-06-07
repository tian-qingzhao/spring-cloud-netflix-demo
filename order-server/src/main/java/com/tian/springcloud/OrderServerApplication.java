package com.tian.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: tian
 * @Date: 2020/2/25 23:56
 * @Desc:
 *        这里使用 @EnableFeignClients注解的时候，需要注意的是如果调用服务方和暴露服务方
 *        不在同一个包下(暴露服务的接口方就是加了@FeignClient注解的)，
 *        这里的启动类会出现扫描不到暴露服务接口的那个类，就注入不到容器中。
 *
 *        Feign超时设置：源码中默认options默认配置的是6000毫秒，但是Feign中加入了Hestrix，此时默认值为1秒
 *        在服务的application配置中添加
 *        1.feign.client.config.default.connect-timeout=2000
 *        2.feign.client.config.default.read-timeout=6000
 *        connect-timeout 响应超时设置
 *        read-timeout 请求超时设置
 *        响应时间：找到接口的时间
 *        请求时间：接口可用返回值处理的时间
 *
 *        Hystrix的熔断和降级:
 *          相同点：1.目的都很一致，都是从可用性可靠性着想，为防止系统的整体缓慢甚至崩溃，采用的技术手段
 *                2.最终表现类似，对于两者来说，最终让用户体验到的是某些功能暂时性的不可用或不可达。
 *          不同点：触发原因不太一样，服务熔断一般是某个服务(下游服务，订单的下游服务是商品)故障引起，
 *                而服务降级是从整体符合考虑。
 *           服务雪崩的解决思路：1.超时机制 2.服务限流 3.服务熔断 4.服务降级
 *           熔断隔离的两种策略：1.线程池(默认的) 2.信号量
 *           进入监控仪表盘的地址： http://域名:端口号/hystrix
 *                              然后在中间的地址栏输入http://域名:端口号/actuator/hystrix.stream
 *
 *         Zuul网关是系统的唯一对外的入口，介于客户端和服务端之间的中间层，处理业务功能，
 *         提供路由请求、鉴权、监控、缓存、限流等功能。
 *
 *         Sleuth链路追踪的参数值：
 *           1.第一个值：spring.application.name的值
 *           2.第二个值：随机字符串，Sleuth生成的一个ID,叫Trace ID，用来标识一条请求路链，一条请求链路中包含一个Trace ID，多个span ID
 *           3.第三个值：span ID的基本工作单元，获取元数据，如发送一个http
 *           4.第四个值：是否要将该信息输出到zipkin服务器中收集和展示。
 *         zipkin服务追踪可视化界面的使用：
 *           1.启动jar包(java -jar 架包名.jar)
 *           2.直接在浏览器访问 http://localhost:9411/zipkin  （默认端口号为9411）
 *           3.在配置文件里面写上 spring.ziplin.base-url=http://localhost:9411/ziplin
 *           补充：想看到每次服务的调用，在配置一行 spring.sleuth.sampler.probability=1  （这两个需要在调用方和被调用方都写上）
 */
@SpringBootApplication
@EnableFeignClients //开启feignClients远程调用
@EnableCircuitBreaker //开启断路器功能
//@SpringCloudApplication //可以取代 @SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker 这三个注解
@EnableHystrixDashboard //开启hystrix监控仪表盘
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

}
