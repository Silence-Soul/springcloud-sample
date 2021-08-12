package com.atguili.gulimall.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class MyCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 允许服务端访问的客户端请求头
        config.addAllowedHeader("*");
        // 允许访问的方法名,GET POST等
        config.addAllowedMethod("*");
        // 设置你要允许的网站域名，如果全允许则设为 *
        config.addAllowedOriginPattern("*");
        source.registerCorsConfiguration("/**" , config);
        return new CorsWebFilter(source);
    }
}
