package com.leandrodeferrari.spring.demo.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "noticias")
@Data
public class Noticia implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String titulo;
    private String cuerpo;
//    @Column(columnDefinition = "MEDIUMTEXT")
    @OneToOne
    private Foto foto;
    @Column(name = "fecha_de_subida")
    private LocalDateTime fechaDeSubida;
    private boolean alta;
    
}
