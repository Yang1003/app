package com.concurrence.app.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.Resource;


/**
 * @author chenqw
 * @date 2022/9/28 8:20
 */
@Configuration
public class CustomWebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    WebRequestHandlerInterceptor webRequestHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webRequestHandlerInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/file/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/druid/**").addResourceLocations("classpath:/META-INF/resources/");

    }

}
