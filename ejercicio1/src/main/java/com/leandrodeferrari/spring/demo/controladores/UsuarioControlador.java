package com.leandrodeferrari.spring.demo.controladores;

import com.leandrodeferrari.spring.demo.entidades.Noticia;
import com.leandrodeferrari.spring.demo.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ingresar")
public class UsuarioControlador {

//    @Autowired
//    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/")
    public String ingreso(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {

            modelo.put("error", "Fallo al ingresar");

        }

        return "ingreso_usuario.html";

    }

    @PreAuthorize("hasAnyRole('ROLE_Administrador')")
    @GetMapping("/inicio")
    public String inicio(ModelMap modelo) {
        
        List<Noticia> noticias = noticiaServicio.ordenarNoticiasPorFechaDescendente();
        modelo.put("noticias", noticias);
        
        return "inicio.html";

    }

}
