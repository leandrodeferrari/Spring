package com.leandrodeferrari.spring.demo.controladores;

import com.leandrodeferrari.spring.demo.entidades.Noticia;
import com.leandrodeferrari.spring.demo.excepciones.NoticiaExcepcion;
//import com.leandrodeferrari.spring.demo.excepciones.UsuarioExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import com.leandrodeferrari.spring.demo.servicios.NoticiaServicio;
//import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
//import com.leandrodeferrari.spring.demo.servicios.UsuarioServicio;
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

//    @Autowired
//    private UsuarioServicio usuarioServicio;
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
    public String guardarNoticia(@RequestParam("titulo") String titulo, @RequestParam("cuerpo") String cuerpo, @RequestParam("foto") MultipartFile foto, ModelMap modelo) {

        try {

            String ruta = "C:\\Users\\pc\\Documents\\ApacheNetBeansProjects\\Spring\\ejercicio1\\src\\main\\resources\\static\\imagenesNoticias";
            int indice = foto.getOriginalFilename().indexOf(".");
            String extension = "";
            extension = "." + foto.getOriginalFilename().substring(indice + 1);
            String nombreFoto = Calendar.getInstance().getTimeInMillis() + extension;
//            Path rutaAbsoluta = id != 0 ? Paths.get(ruta + "//"+foto) :
//                    Paths.get(ruta+"//"+nombreFoto);
            Path rutaAbsoluta = Paths.get(ruta + "/" + nombreFoto);
            try {
                Files.write(rutaAbsoluta,foto.getBytes());
//                File archivo = new File(rutaAbsoluta.toString());
//                archivo.createNewFile();
//                foto.transferTo(archivo);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                modelo.put("error", "Sucedió un error con su foto");
            }
            noticiaServicio.crearNoticia(titulo, cuerpo, nombreFoto);

            modelo.put("exito", "La noticia se ha creado correctamente");
            return "registro_noticia.html";

        } catch (NoticiaExcepcion ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("titulo", titulo);
            modelo.put("cuerpo", cuerpo);
            modelo.put("foto", foto);

            return "registro_noticia.html";

        }

    }

    //PutMapping
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
