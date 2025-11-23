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
        
        // Map đường dẫn /uploads/** tới thư mục uploads/ trong project
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}