package com.leandrodeferrari.spring.demo.servicios;

import com.leandrodeferrari.spring.demo.entidades.Foto;
import com.leandrodeferrari.spring.demo.entidades.Noticia;
import com.leandrodeferrari.spring.demo.excepciones.FotoExcepcion;
import com.leandrodeferrari.spring.demo.excepciones.NoticiaExcepcion;
import com.leandrodeferrari.spring.demo.repositorios.NoticiaRepositorio;
import java.time.LocalDateTime;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NoticiaServicio {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Autowired
    private FotoServicio fotoServicio;

    @Transactional
    public Noticia crearNoticia(String titulo, String cuerpo, MultipartFile archivo) throws FotoExcepcion {
        
        validar(titulo, cuerpo, archivo);

        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        Foto foto = fotoServicio.guardar(archivo);
        noticia.setFoto(foto);
        noticia.setFechaDeSubida(LocalDateTime.now());
        noticia.setAlta(true);

        noticiaRepositorio.save(noticia);

        return noticia;

    }

    @Transactional
    public boolean modificarNoticia(String id, String titulo, String cuerpo, MultipartFile archivo) throws FotoExcepcion {

        validarId(id);
        validar(titulo, cuerpo, archivo);

        boolean esModificado = false;
        Optional<Noticia> respuestaNoticia = noticiaRepositorio.findById(id);

        if (respuestaNoticia.isPresent()) {
            
            Noticia noticia = respuestaNoticia.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            Long idImagen; idImagen = noticia.getFoto().getId();
            Foto foto = fotoServicio.actualizar(archivo, idImagen);
            noticia.setFoto(foto);
            
            noticiaRepositorio.save(noticia);
            esModificado = true;
        }

        return esModificado;

    }

    @Transactional
    public boolean eliminarNoticia(String id) {

        validarId(id);

        boolean esBorrado = false;
        Optional<Noticia> respuestaNoticia = noticiaRepositorio.findById(id);

        if (respuestaNoticia.isPresent()) {
            
            Noticia noticia = respuestaNoticia.get();
            
            if(noticia.isAlta()){
                
                noticia.setAlta(false);
                noticiaRepositorio.save(noticia);
                esBorrado = true;
                
            }
            
        }

        return esBorrado;

    }

    public List<Noticia> ordenarNoticiasPorFechaDescendente() {

        List<Noticia> noticias = noticiaRepositorio.buscarNoticiasOrdenadasDescendentementePorFecha();

        return noticias;

    }

    public Noticia encontrarNoticia(String id) {

        validarId(id);

        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            return noticia;
        } else {
            return new Noticia();
        }

    }

    public List<Noticia> listarNoticiasDadasDeAlta(){
        
        List<Noticia> noticias = noticiaRepositorio.buscarNoticiasDadasDeAlta();
        
        return noticias;
        
    }
    
    private void validarId(String id) {

        if (id == null) {
            throw new NoticiaExcepcion("Ha ingresado un valor nulo en el ID");
        }
        if (id.isEmpty()) {
            throw new NoticiaExcepcion("Ha ingresado el ID vacío");
        }

    }

    private void validar(String titulo, String cuerpo, MultipartFile foto) {

        if (titulo == null) {
            throw new NoticiaExcepcion("Ha ingresado un valor nulo en el título de la noticia");
        }
        
        if (titulo.isEmpty()) {
            throw new NoticiaExcepcion("Ha ingresado el título vacío");
        }

        if (cuerpo == null) {
            throw new NoticiaExcepcion("Ha ingresado un valor nulo en el cuerpo de la noticia");
        }
        
        if (cuerpo.isEmpty()) {
            throw new NoticiaExcepcion("Ha ingresado el cuerpo vacío");
        }

        if (foto == null) {
            throw new NoticiaExcepcion("Ha ingresado un valor nulo en la foto de la noticia");
        }
        
    }

}
