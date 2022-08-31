package com.leandrodeferrari.spring.demo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nombre_de_usuario")
    private String nombreDeUsuario;
    private String contrasenia;
    private String email;
    @Column(name = "fecha_de_alta")
    private LocalDate fechaDeAlta;
    private String rol;
    
}
