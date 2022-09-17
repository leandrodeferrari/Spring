package com.leandrodeferrari.spring.demo.controladores;

import com.leandrodeferrari.spring.demo.entidades.Noticia;
import com.leandrodeferrari.spring.demo.excepciones.FotoExcepcion;
import com.leandrodeferrari.spring.demo.excepciones.NoticiaExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import com.leandrodeferrari.spring.demo.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/acceso")
    public String cargarPaginaDeAdmin(ModelMap modelo) {

        List<Noticia> noticias = noticiaServicio.listarNoticiasDadasDeAlta();

        modelo.addAttribute("noticias", noticias);

        return "acceso_admin.html";
    }

    @GetMapping("/noticias")
    public String cargaPaginaDeCrearNoticia() {
        return "registro_noticia.html";
    }

    @PostMapping("/guardar-noticia")
    public String guardarNoticia(@RequestParam("titulo") String titulo, @RequestParam("cuerpo") String cuerpo, @RequestParam("archivo") MultipartFile archivo, ModelMap modelo) throws FotoExcepcion {

        try {

            noticiaServicio.crearNoticia(titulo, cuerpo, archivo);

            modelo.put("exito", "La noticia se ha creado correctamente");
            return "registro_noticia.html";

        } catch (NoticiaExcepcion ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("titulo", titulo);
            modelo.put("cuerpo", cuerpo);
            modelo.put("archivo", archivo);

            return "registro_noticia.html";

        }

    }

    //PutMapping
    @PostMapping("/modificar-noticia")
    public String moodificarNoticia(@RequestParam("id") String id, @RequestParam("titulo") String titulo, @RequestParam("cuerpo") String cuerpo, @RequestParam("archivo") MultipartFile archivo, ModelMap modelo) throws FotoExcepcion {

        try {

            boolean esModificada = noticiaServicio.modificarNoticia(id, titulo, cuerpo, archivo);

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

    //PutMapping
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
