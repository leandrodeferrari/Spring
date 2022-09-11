package com.leandrodeferrari.spring.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FotoConfiguracion implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registro){
        
        WebMvcConfigurer.super.addResourceHandlers(registro);
        registro.addResourceHandler("/imagenesNoticias/**").addResourceLocations("file:/C:/noticias/imagenesNoticias/");
        
    }
    
}
