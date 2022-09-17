package com.leandrodeferrari.spring.demo.excepciones;

public class UsuarioExcepcion extends RuntimeException {

    public UsuarioExcepcion(String mensaje) {
        
        super(mensaje);
        
    }
    
}
