package com.leandrodeferrari.spring.demo.repositorios;

import com.leandrodeferrari.spring.demo.entidades.Usuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
    public Usuario findByEmail(String email);
   
    public Usuario findByNombreDeUsuario(String nombreDeUsuario);
    
}
