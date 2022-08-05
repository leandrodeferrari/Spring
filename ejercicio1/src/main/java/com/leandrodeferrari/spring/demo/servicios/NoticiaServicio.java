package com.leandrodeferrari.spring.demo.servicios;

import com.leandrodeferrari.spring.demo.entidades.Noticia;
import com.leandrodeferrari.spring.demo.excepciones.NoticiaExcepcion;
import com.leandrodeferrari.spring.demo.repositorios.NoticiaRepositorio;
import java.time.LocalDateTime;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public Noticia crearNoticia(String titulo, String cuerpo, String foto){
        
        validar(titulo, cuerpo, foto);
        
        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setFoto(foto);
        noticia.setFechaDeSubida(LocalDateTime.now());
        
        noticiaRepositorio.save(noticia);
        
        return noticia;
        
    }
    
    @Transactional
    public boolean modificarNoticia(String id, String titulo, String cuerpo, String foto){
        
        validarId(id);
        validar(titulo, cuerpo, foto);
        
        boolean esModificado = false;
        Optional<Noticia> respuestaNoticia = noticiaRepositorio.findById(id);
        
        if(respuestaNoticia.isPresent()){
            Noticia noticia = respuestaNoticia.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setFoto(foto);
            noticiaRepositorio.save(noticia);
            esModificado = true;
        }
        
        return esModificado;
        
    }
    
    @Transactional
    public boolean eliminarNoticia(String id){
        
        validarId(id);
        
        boolean esBorrado = false;
        Optional<Noticia> respuestaNoticia = noticiaRepositorio.findById(id);
        
        if(respuestaNoticia.isPresent()){
            Noticia noticia = respuestaNoticia.get();
            noticiaRepositorio.delete(noticia);
            esBorrado = true;
        }
        
        return esBorrado;
        
    }
    
    public List<Noticia> ordenarNoticiasPorFechaDescendente(){
        
        List<Noticia> noticias = noticiaRepositorio.buscarNoticiasOrdenadasDescendentementePorFecha();
         
        return noticias;
        
    }
    
    private void validarId(String id){
        
        if(id == null){
            throw new NoticiaExcepcion("Ha ingresado un valor nulo en su ID");
        } 
        if(id.isEmpty()){
            throw new NoticiaExcepcion("Ha ingresado el ID vacío");
        }
        
    }
    
    private void validar(String titulo, String cuerpo, String foto){
        
        if(titulo == null){
            throw new NoticiaExcepcion("Ha ingresado un valor nulo en su título, de la noticia");
        }
        if(titulo.isEmpty()){
            throw new NoticiaExcepcion("Ha ingresado el título vacío");
        }
        
        if(cuerpo == null){
            throw new NoticiaExcepcion("Ha ingresado un valor nulo en su cuerpo, de la noticia");
        }
        if(cuerpo.isEmpty()){
            throw new NoticiaExcepcion("Ha ingresado el cuerpo de la noticia, vacía");
        }
        
        
        if(foto == null){
            throw new NoticiaExcepcion("Ha ingresado un valor nulo, en su foto de la noticia");
        }
        if(foto.isEmpty()){
            throw new NoticiaExcepcion("No ha ingresado foto, de la noticia");
        }
        
    }
    
}
