package com.leandrodeferrari.spring.demo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "periodistas")
@Getter
@Setter
public class Periodista extends Usuario implements Serializable {

    @Column(name = "cantidad_noticias")
    private ArrayList cantidadNoticias;
    @Column(name = "sueldo_mensual")
    private double sueldoMensual;

    public Periodista() {
    }
    
}
