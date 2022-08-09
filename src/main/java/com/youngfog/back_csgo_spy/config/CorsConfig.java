package com.youngfog.back_csgo_spy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//                允许跨域路径
        registry.addMapping("/**")
//                允许的域名
                .allowedOriginPatterns("*")
//                是否允许cookie
                .allowCredentials(true)
//                方式
                .allowedMethods("GET","POST","DELETE","PUT")
//                允许的header属性
                .allowedHeaders("*")
//                允许时间
                .maxAge(3600);
    }
}