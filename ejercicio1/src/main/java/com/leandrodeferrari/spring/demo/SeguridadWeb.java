package com.leandrodeferrari.spring.demo;

import com.leandrodeferrari.spring.demo.enumeraciones.Rol;
import com.leandrodeferrari.spring.demo.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb extends WebSecurityConfigurerAdapter{
    
    @Autowired
    public UsuarioServicio usuarioServicio;
    
//    @Autowired
//    private BCryptPasswordEncoder bCrypt;
//    
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        
//        return bCryptPasswordEncoder;
//        
//    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServicio)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
//                .antMatchers("/registrar/*").hasRole(Rol.ADMIN.getNombreRol())
                .antMatchers("/css/*","/js/*","/imagenes/*","/**").permitAll()
            .and().formLogin()
                .loginPage("/ingresar/")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("email")
                .passwordParameter("contrasenia")
                .defaultSuccessUrl("/ingresar/inicio")
                .permitAll()
            .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            .and().csrf()
                 .disable();
    
    }
    
}
