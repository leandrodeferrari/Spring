package com.leandrodeferrari.spring.demo.controladores;

import com.leandrodeferrari.spring.demo.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    
//    @GetMapping("/crear")
//    public String agregar(){
//        return "noticias.html";
//    }
    
}
