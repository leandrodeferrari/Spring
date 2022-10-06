package com.leandrodeferrari.spring.demo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "administradores")
@Getter
@Setter
public class Administrador extends Usuario implements Serializable {

    @Column(name = "nivel_permisos")
    private Integer nivelPermisos;

    public Administrador() {
    }

}
