package com.leandrodeferrari.spring.demo.servicios;

import com.leandrodeferrari.spring.demo.entidades.Usuario;
import com.leandrodeferrari.spring.demo.enumeraciones.Rol;
import com.leandrodeferrari.spring.demo.excepciones.UsuarioExcepcion;
import com.leandrodeferrari.spring.demo.repositorios.UsuarioRepositorio;
import java.time.LocalDate;
import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public boolean crearUsuario(String nombreDeUsuario, String contrasenia1, String contrasenia2, String email) {

        validar(nombreDeUsuario, contrasenia1, contrasenia2, email);

        Usuario usuario = new Usuario();

        usuario.setNombreDeUsuario(nombreDeUsuario);
        usuario.setContrasenia(new BCryptPasswordEncoder().encode(contrasenia1));
        usuario.setEmail(email);
        usuario.setFechaDeAlta(LocalDate.now());
        
        if(usuario.getNombreDeUsuario().contains("admin")){
            
            usuario.setRol(Rol.ADMIN.getNombre());
            
        } else if(usuario.getNombreDeUsuario().contains("periodista")){
            
          usuario.setRol(Rol.PERIODISTA.getNombre());
            
        } else {
            
        usuario.setRol(Rol.USUARIO.getNombre());
        
        }
        
        try {
            
            usuarioRepositorio.save(usuario);
            
            return true;

        } catch (UsuarioExcepcion ex) {
            
            throw new UsuarioExcepcion("Error al registrar el usuario");
            
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.findByEmail(email);
        
        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority permiso = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());

            permisos.add(permiso);

            ServletRequestAttributes atributo = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession sesion = atributo.getRequest().getSession(true);

            sesion.setAttribute("UsuarioSesion", usuario);

            return new User(usuario.getEmail(), usuario.getContrasenia(), permisos);

        } else {
            
            throw new UsernameNotFoundException("Error al cargar usuario");
            
        }

    }

    private void validar(String nombreDeUsuario, String contrasenia1, String contrasenia2, String email) {

        if (nombreDeUsuario == null) {
            throw new UsuarioExcepcion("Ha ingresado un valor nulo en el nombre del usuario");
        }

        if (nombreDeUsuario.isEmpty()) {
            throw new UsuarioExcepcion("Ha ingresado un valor vacío en el nombre del usuario");
        }

        if (contrasenia1.length() <= 7) {
            throw new UsuarioExcepcion("Ha ingresado una contraseña con menos de 8 caracteres");
        }
        
        if (contrasenia1 == null) {
            throw new UsuarioExcepcion("Ha ingresado un valor nulo en la contraseña del usuario");
        }

        if (contrasenia1.isEmpty()) {
            throw new UsuarioExcepcion("Ha ingresado un valor vacío en la contraseña del usuario");
        }

        if (contrasenia2.length() <= 7) {
            throw new UsuarioExcepcion("Ha ingresado una contraseña con menos de 8 caracteres");
        }
        
        if (contrasenia2 == null) {
            throw new UsuarioExcepcion("Ha ingresado un valor nulo en la contraseña del usuario");
        }

        if (contrasenia2.isEmpty()) {
            throw new UsuarioExcepcion("Ha ingresado un valor vacío en la contraseña del usuario");
        }

        if (!(contrasenia1.equals(contrasenia2))) {
            throw new UsuarioExcepcion("Sus contraseñas no coinciden");
        }

        if (email == null) {
            throw new UsuarioExcepcion("Ha ingresado un valor nulo en el email del usuario");
        }

        if (email.isEmpty()) {
            throw new UsuarioExcepcion("Ha ingresado un valor vacío en el email del usuario");
        }

    }

}
