package com.leandrodeferrari.spring.demo.excepciones;

import java.io.IOException;

public class FotoExcepcion extends IOException {
    
    public FotoExcepcion(String mensaje){
        
        super(mensaje);
        
    }
    
}
