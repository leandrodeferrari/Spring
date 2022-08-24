package com.leandrodeferrari.spring.demo.controladores;

import com.leandrodeferrari.spring.demo.entidades.Noticia;
import com.leandrodeferrari.spring.demo.excepciones.NoticiaExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import com.leandrodeferrari.spring.demo.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ingresar")
public class PanelAdminControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/")
    public String cargarRegistroUsuario() {
        return "registro_usuario.html";
    }

    @GetMapping("/admin")
    public String cargarPaginaDeAdmin(ModelMap modelo) {

        List<Noticia> noticias = noticiaServicio.listarNoticiasDadasDeAlta();

        modelo.addAttribute("noticias", noticias);

        return "acceso_admin.html";
    }

    @GetMapping("/admin/crear")
    public String cargaPaginaDeCrearNoticia() {
        return "registro_noticia.html";
    }

    @PostMapping("/cargar-noticia")
    public String cargarNoticia(@RequestParam("titulo") String titulo, @RequestParam("cuerpo") String cuerpo, @RequestParam("foto") String foto, ModelMap modelo) {

        try {

            noticiaServicio.crearNoticia(titulo, cuerpo, foto);

            modelo.put("exito", "La noticia se ha creado correctamente");
            return "registro_noticia.html";

        } catch (NoticiaExcepcion ex) {

            modelo.put("error", ex.getMessage());
            return "registro_noticia.html";

        }

    }

    @PostMapping("/modificar-noticia")
    public String moodificarNoticia(@RequestParam("id") String id, @RequestParam("titulo") String titulo, @RequestParam("cuerpo") String cuerpo, @RequestParam("foto") String foto, ModelMap modelo) {

        try {

            boolean esModificada = noticiaServicio.modificarNoticia(id, titulo, cuerpo, foto);

            if (esModificada) {
                modelo.put("exitoModificar", "La noticia se ha modificado correctamente");
            } else {
                modelo.put("errorModificar", "No existe noticia con ese ID");
            }

            List<Noticia> noticias = noticiaServicio.listarNoticiasDadasDeAlta();
            modelo.addAttribute("noticias", noticias);
            return "acceso_admin.html";

        } catch (NoticiaExcepcion ex) {

            modelo.put("errorModificar", "La noticia no pudo modificarse. Causa: " + ex.getMessage());

            List<Noticia> noticias = noticiaServicio.listarNoticiasDadasDeAlta();
            modelo.addAttribute("noticias", noticias);
            return "acceso_admin.html";

        }

    }

    @PostMapping("/eliminar-noticia")
    public String eliminarNoticia(@RequestParam("id") String id, ModelMap modelo) {

        try {

            boolean esBorrada = noticiaServicio.eliminarNoticia(id);

            if (esBorrada) {
                modelo.put("exitoEliminar", "La noticia se ha borrado correctamente");
            } else {
                modelo.put("errorEliminar", "No existe noticia con ese ID");
            }

            List<Noticia> noticias = noticiaServicio.listarNoticiasDadasDeAlta();
            modelo.addAttribute("noticias", noticias);
            return "acceso_admin.html";

        } catch (NoticiaExcepcion ex) {

            modelo.put("errorEliminar", "La noticia no pudo borrarse. Causa: " + ex.getMessage());
            
            List<Noticia> noticias = noticiaServicio.listarNoticiasDadasDeAlta();
            modelo.addAttribute("noticias", noticias);
            return "acceso_admin.html";

        }

    }

}
