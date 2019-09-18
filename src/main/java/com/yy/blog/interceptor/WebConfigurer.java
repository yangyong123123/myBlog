package com.yy.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author user
 * @data 2019/8/23 11:38
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    //addPathPatterns("/**")设置拦截路径
    //excludePathPatterns("/admin/login")设置白名单
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login","/admin/")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/lib/**");
    }


}
