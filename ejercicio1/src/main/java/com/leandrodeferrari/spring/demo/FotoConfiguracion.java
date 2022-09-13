package com.leandrodeferrari.spring.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FotoConfiguracion implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registro){
        
        WebMvcConfigurer.super.addResourceHandlers(registro);
        
        registro.addResourceHandler("/imagenesNoticias/**").
                addResourceLocations("C:/Users/pc/Documents/ApacheNetBeansProjects/Spring/ejercicio1/src/main/resources/static/imagenesNoticias/");
         registro.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    
}
