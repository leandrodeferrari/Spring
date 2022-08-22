package com.leandrodeferrari.spring.demo.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "noticias")
public class Noticia implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String titulo;
    private String cuerpo;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String foto;
    @Column(name = "fecha_de_subida")
    private LocalDateTime fechaDeSubida;
    private boolean alta;

    public Noticia() {
    }

    public String getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDateTime getFechaDeSubida() {
        return fechaDeSubida;
    }

    public void setFechaDeSubida(LocalDateTime fechaDeSubida) {
        this.fechaDeSubida = fechaDeSubida;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }
    
}
