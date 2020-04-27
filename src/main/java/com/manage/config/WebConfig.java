package com.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *跨域请求配置
 */
@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**");
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("GET","PUT","POST","DELETE")
                        .allowedOrigins("*");
            }
        };
    }
}