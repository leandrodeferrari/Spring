package com.leandrodeferrari.spring.demo.repositorios;

import com.leandrodeferrari.spring.demo.entidades.Noticia;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String> {

    @Query("SELECT e FROM Noticia e WHERE e.titulo = :titulo")
    public Noticia buscarPorTitulo(@Param("titulo") String titulo);

    @Query(value = "SELECT * FROM noticias ORDER BY fecha_de_subida DESC", nativeQuery = true)
    public List<Noticia> buscarNoticiasOrdenadasDescendentementePorFecha();

}
