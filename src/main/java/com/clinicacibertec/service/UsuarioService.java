package com.clinicacibertec.service;

import com.clinicacibertec.model.Usuario;

import java.util.List;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> buscarPorCorreo(String correo);

    List<Usuario> listar();
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(Integer id);
    void eliminar(Integer id);
    
}