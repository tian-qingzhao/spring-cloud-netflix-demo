package com.tian.springcloud.orderserver.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: tian
 * @Date: 2020/2/24 18:16
 * @Desc:
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 帮助我们发送http的调用
     * @return
     *  注解@LoadBalanced 表示开启了负载均衡的功能，这个注解是用来给RestTemplate做标记，以使用LoadBalancerClient来配置它
     *
     *  LoadBalancerClient是一个接口，该接口中有三个方法，我们来大概看一下这几个方法的作用：
     *   1.ServiceInstance choose(String serviceId)根据传入的服务名serviceId从客户端负载均衡器中挑选一个对应服务的实例。
     *   2.T execute() ,使用从负载均衡器中挑选出来的服务实例来执行请求。
     *   3.URI reconstructURI(ServiceInstance instance, URI original)表示为系统构建一个合适的URI，
     *   我们在Spring Cloud中服务的发现与消费一文中发送请求时使用了服务的逻辑名称(http://HELLO-SERVICE/hello)而不是具体的服务地址，
     *   在reconstructURI方法中，第一个参数ServiceInstance实例是一个带有host和port的具体服务实例，第二个参数URI则是使用逻辑服务名定义为host和port的URI，
     *   而返回的URI则是通过ServiceInstance的服务实例详情拼接出的具体的host:port形式的请求地址。
     *   一言以蔽之，就是把类似于http://HELLO-SERVICE/hello这种地址转为类似于http://195.124.207.128/hello地址（IP地址也可能是域名）。
     *
     *   OK，找到了LoadBalancerClient还不够，那么具体的配置是在哪里执行的呢?我们在LoadBalancerClient的包下面发现了一个类叫做LoadBalancerAutoConfiguration,
     *   看名字有点像是客户端负载均衡服务器的自动化配置类.
     *   Ribbon默认采用的轮循策略：比如有两台服务的话，会轮流着去请求他们，如果有一个挂了，会请求另外一个，然后过一段时间再去请求挂的那个重新尝试连接。
     *   有好几种负载均衡的策略：随机的、权重的、
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
