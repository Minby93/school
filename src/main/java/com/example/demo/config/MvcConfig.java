package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/").setViewName("welcome");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/"); // указывает на путь к директории со статическими ресурсами
    }

}