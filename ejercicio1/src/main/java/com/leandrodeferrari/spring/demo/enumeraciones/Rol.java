package com.leandrodeferrari.spring.demo.enumeraciones;

public enum Rol {
    
    USUARIO("Usuario"), ADMIN("Administrador"), PERIODISTA("Periodista");
    
    private final String nombreRol;

    private Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    
    public String getNombreRol() {
        return nombreRol;
    }
    
}
