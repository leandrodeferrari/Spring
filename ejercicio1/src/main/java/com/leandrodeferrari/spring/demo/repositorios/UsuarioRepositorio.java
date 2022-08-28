package com.leandrodeferrari.spring.demo.repositorios;

import com.leandrodeferrari.spring.demo.entidades.Usuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
    @Query(value = "SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    
    
    
}
