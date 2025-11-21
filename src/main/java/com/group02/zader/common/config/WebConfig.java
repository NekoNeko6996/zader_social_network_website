package com.group02.zader.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cấu hình để load ảnh upload từ thư mục uploads (nếu có)
        // registry.addResourceHandler("/uploads/**")
        //        .addResourceLocations("file:uploads/");
        
        // Cấu hình mặc định cho static resources (đã có sẵn trong Spring Boot nhưng khai báo lại cho rõ)
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}