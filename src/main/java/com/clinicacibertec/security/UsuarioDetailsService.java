package com.clinicacibertec.security;

import com.clinicacibertec.model.Usuario;
import com.clinicacibertec.repository.UsuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);

        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getCorreo())
                .password(usuario.getClave())
                .roles(usuario.getRol().name())
                .build();
    }
}