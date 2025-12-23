package com.example.demo.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Technical Requirement: 
 * 1. Must use @ServletComponentScan to detect com.example.demo.servlet.HelloServlet.
 * 2. Configure CORS to allow cross-origin requests for testing and Swagger.
 */
@Configuration
@ServletComponentScan(basePackages = "com.example.demo.servlet")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("*") // In production, replace * with specific domains
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}