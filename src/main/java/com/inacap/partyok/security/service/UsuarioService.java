package com.inacap.partyok.security.service;

import com.inacap.partyok.security.entity.Usuario;
import com.inacap.partyok.security.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private UsuarioRepository usuarioRepository;


    /**
     * Metodo que verifica si el usuario que inicia sesion es correcto
     * */
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByCorreo(correo);

        if(usuario == null) {
            logger.error("Error en el login: no existe el usuario '"+correo+"' en el sistema!");
            throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+correo+"' en el sistema!");
        }

        List<GrantedAuthority> authorities = usuario.getRols()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getCorreo(), usuario.getContrasena(), !usuario.getEliminado(), true, true, true, authorities);
    }




}
