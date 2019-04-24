package com.wrial.demo.config;

import com.wrial.demo.compenet.MyLocaleResolver;
import com.wrial.demo.interceptor.MyLoginInterceptor;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class Myconfiguration extends WebMvcConfigurerAdapter {

//    ServerProperties里边就是所有的servlet容器的配置
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("/login");
                registry.addViewController("/index.html").setViewName("/login");
                registry.addViewController("/main.html").setViewName("/dashboard");

            }
        };
        return adapter;
    }

    @Override
    //拦截器
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        //不需要对静态资源设置，因为SpringBoot默认不拦截静态资源
        registry.addInterceptor(new MyLoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/", "/user/login");

    }
}
