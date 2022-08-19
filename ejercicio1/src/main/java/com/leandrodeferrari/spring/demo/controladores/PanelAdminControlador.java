package com.leandrodeferrari.spring.demo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import com.leandrodeferrari.spring.demo.servicios.NoticiaServicio;
//import net.iharder.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ingresar")
public class PanelAdminControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/")
    public String cargarRegistroUsuario() {
        return "registro_usuario.html";
    }
    
    @GetMapping("/acceso")
    public String cargarPaginaDeAdmin() {
        return "acceso_admin.html";
    }
    
    @GetMapping("/acceso/crear")
    public String cargaPaginaDeCrearNoticia() {
        return "registro_noticia.html";
    }
    
    @PostMapping("/cargar-noticia")
    public String cargarNoticia(@RequestParam("titulo") String titulo, @RequestParam("cuerpo") String cuerpo, @RequestParam("foto") MultipartFile foto) {
        
        noticiaServicio.crearNoticia(titulo, cuerpo, foto);

        //Falta validar error
        
        return "index.html";
        
    }
    
    @PostMapping("/modificar-noticia")
    public String moodificarNoticia(@RequestParam("titulo") String titulo, @RequestParam("cuerpo") String cuerpo, @RequestParam("foto") MultipartFile foto) {
        
        noticiaServicio.crearNoticia(titulo, cuerpo, foto);

        //Falta validar error
        // Hacer metodo
        return "index.html";
        
    }
    
    @PostMapping("/eliminar-noticia")
    public String eliminarNoticia(@RequestParam("titulo") String titulo, @RequestParam("cuerpo") String cuerpo, @RequestParam("foto") MultipartFile foto) {
        
        noticiaServicio.crearNoticia(titulo, cuerpo, foto);

        //Falta validar error
        // Hacer metodo
        return "index.html";
        
    }
    
}
