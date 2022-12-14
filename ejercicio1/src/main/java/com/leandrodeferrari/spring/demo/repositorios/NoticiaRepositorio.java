package com.leandrodeferrari.spring.demo.repositorios;

import com.leandrodeferrari.spring.demo.entidades.Noticia;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String> {
    
    @Query(value = "SELECT * FROM noticias WHERE alta = 1 ORDER BY fecha_de_subida DESC", nativeQuery = true)
    public List<Noticia> buscarNoticiasOrdenadasDescendentementePorFecha();

    @Query(value= "SELECT * FROM noticias WHERE alta = 1", nativeQuery = true)
    public List<Noticia> buscarNoticiasDadasDeAlta();
    
}
