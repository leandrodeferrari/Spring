package com.leandrodeferrari.spring.demo.enumeraciones;

public enum Rol {
    
    USUARIO("Usuario"), ADMIN("Administrador"), PERIODISTA("Periodista");
    
    private final String nombre;

    private Rol(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
}
