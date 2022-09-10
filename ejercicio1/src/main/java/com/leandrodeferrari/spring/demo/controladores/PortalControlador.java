package com.leandrodeferrari.spring.demo.controladores;

import com.leandrodeferrari.spring.demo.entidades.Noticia;
import com.leandrodeferrari.spring.demo.entidades.Usuario;
//import com.leandrodeferrari.spring.demo.enumeraciones.Rol;
import com.leandrodeferrari.spring.demo.servicios.NoticiaServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/") // Revisar, en otros proyectos, como ponen la ruta del index
public class PortalControlador {
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @GetMapping("/")
    public String index(ModelMap modelo){
        
        List<Noticia> noticias = noticiaServicio.ordenarNoticiasPorFechaDescendente();
        modelo.put("noticias", noticias);
        
        return "index.html";
    
    }
    
    @GetMapping("/ingresar")
    public String ingreso(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {

            modelo.put("error", "Fallo al ingresar");

        }

        return "ingreso_usuario.html";

    }

    @PreAuthorize("hasAnyRole('ROLE_Administrador')")
    @GetMapping("/ingresar/inicio")
    public String inicio(ModelMap modelo, HttpSession sesion) {
        
        Usuario usuarioIngresado = (Usuario) sesion.getAttribute("UsuarioSesion");
        
        modelo.put("nombreUsuario", "¡Hola, " + usuarioIngresado.getNombreDeUsuario() + "!");
        
//        if(usuarioIngresado.getRol().equals(Rol.ADMIN.getNombre())){
//            return "redirect:/";
//        }
        
        List<Noticia> noticias = noticiaServicio.ordenarNoticiasPorFechaDescendente();
        modelo.put("noticias", noticias);
        
        return "inicio.html";

    }
    
}
