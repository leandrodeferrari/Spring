package com.leandrodeferrari.spring.demo.controladores;

import com.leandrodeferrari.spring.demo.entidades.Noticia;
import com.leandrodeferrari.spring.demo.excepciones.NoticiaExcepcion;
import com.leandrodeferrari.spring.demo.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/noticias")
public class NoticiaControlador {
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @GetMapping("/")
    public String noticias(){
        return "noticias.html";
    }
    
    @GetMapping("/{id}")
    public String mostrarNoticia(@PathVariable("id") String id, ModelMap modelo){
        
        try {
            
            Noticia noticia = noticiaServicio.encontrarNoticia(id);
            modelo.addAttribute("titulo", noticia.getTitulo());
            modelo.addAttribute("cuerpo", noticia.getCuerpo());
            modelo.put("fechaDeSubida", noticia.getFechaDeSubida());
            modelo.put("id", noticia.getId());
            
        } catch (NoticiaExcepcion ex) {
            modelo.put("errorCargarNoticia", "Error al cargar la noticia");
        }
        
        return "noticias.html";
        
    }
    
}
