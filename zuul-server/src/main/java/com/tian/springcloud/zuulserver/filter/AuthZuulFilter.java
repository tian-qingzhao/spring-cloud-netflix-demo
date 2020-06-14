package com.tian.springcloud.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: tian
 * @Date: 2020/2/27 17:54
 * @Desc:
 */
@Component
public class AuthZuulFilter extends ZuulFilter {

    /**
     * 前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 启动的级别（数字越大，优先级越低）
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * true代表生效，false代表不生效。那么什么情况下使用不生效呢，不生效干嘛还要写这个filter类呢？
     * 其实是有用的，有时我们会动态的决定让不让一个filter生效，譬如我们可能根据Request里是否携带某个参数来判断是否需要生效，
     * 或者我们需要从上一个filter里接收某个数据来决定，再或者我们希望能手工控制是否生效（使用如Appolo之类的配置中心，来动态设置该字段）。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        /*RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader("token");
        String cookie = request.getHeader("Cookie");
        System.out.println("token:" + token + ",cookie:" + cookie);
        String requestURI = request.getRequestURI();
        if(requestURI.indexOf("/order/server") >=0){
            // 是否执行该过滤器，此处为true，说明需要过滤，然后会执行下面的run()方法
            return true;
        }
        return false;*/

        //真实项目中直接返回true，代表所有的请求全部需要拦截
        return true;
    }

    /**
     * 这个是主要的处理逻辑的地方，我们做权限控制、日志等都是在这里。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String cookie = request.getHeader("token");
        if(StringUtils.isEmpty(cookie)){
            //可能通过地址栏的参数发送请求
            cookie = request.getParameter("token");
        }
        if(StringUtils.isEmpty(cookie)){
            //过滤该请求，不对其进行路由(对该请求禁止路由，禁止访问下游服务)
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        //这里return的值没有意义，zuul框架没有使用该返回值
        return null;

    }
}
