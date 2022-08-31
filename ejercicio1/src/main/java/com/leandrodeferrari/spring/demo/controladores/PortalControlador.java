package com.leandrodeferrari.spring.demo.controladores;

import com.leandrodeferrari.spring.demo.entidades.Noticia;
import com.leandrodeferrari.spring.demo.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @GetMapping("/")
    public String index(ModelMap modelo){
        List<Noticia> noticias = noticiaServicio.ordenarNoticiasPorFechaDescendente();
        modelo.put("noticias", noticias);
    return "index.html";
    }
    
//    @GetMapping("/")
//    public String cargarRegistroUsuario() {
//        return "registro_usuario.html";
//    }
    
}
