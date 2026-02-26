package com.clinicacibertec.service;

import com.clinicacibertec.model.Usuario;
import com.clinicacibertec.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return repo.findByCorreo(correo);
    }

    @Override
    public List<Usuario> listar() {
        return repo.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}