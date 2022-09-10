package com.leandrodeferrari.spring.demo.controladores;

//import com.leandrodeferrari.spring.demo.entidades.Noticia;
import com.leandrodeferrari.spring.demo.excepciones.UsuarioExcepcion;
//import com.leandrodeferrari.spring.demo.servicios.NoticiaServicio;
import com.leandrodeferrari.spring.demo.servicios.UsuarioServicio;
//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registrar")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String cargarRegistroUsuario() {
        return "registro_usuario.html";
    }

    @PostMapping("/registrar-usuario")
    public String registrarUsuario(@RequestParam("nombreDeUsuario") String nombreDeUsuario, @RequestParam("email") String email, @RequestParam("contrasenia1") String contrasenia1, @RequestParam("contrasenia2") String contrasenia2, ModelMap modelo) {

        try {

            usuarioServicio.crearUsuario(nombreDeUsuario, contrasenia1, contrasenia2, email);

            modelo.put("exito", "Te has registrado correctamente");
            return "registro_usuario.html";

        } catch (UsuarioExcepcion ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombreDeUsuario", nombreDeUsuario);
            modelo.put("email", email);
            return "registro_usuario.html";

        }

    }

}
