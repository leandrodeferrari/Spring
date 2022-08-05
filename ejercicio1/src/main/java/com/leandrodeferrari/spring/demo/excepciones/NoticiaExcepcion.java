package com.leandrodeferrari.spring.demo.excepciones;

public class NoticiaExcepcion extends RuntimeException {

    public NoticiaExcepcion(String mensaje) {
        super(mensaje);
    }    
    
}
