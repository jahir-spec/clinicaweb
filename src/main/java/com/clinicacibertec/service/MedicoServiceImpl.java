package com.clinicacibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinicacibertec.model.Medico;
import com.clinicacibertec.model.Usuario;
import com.clinicacibertec.repository.MedicoRepository;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository repo;

    @Override
    public Medico guardar(Medico medico) {
        return repo.save(medico);
    }
    @Override
    public Optional<Medico> buscarPorId(Integer id) {
        return repo.findById(id);
    }
    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
    @Override
    public Optional<Medico> buscarPorUsuario(Usuario usuario) {
        return repo.findByUsuario(usuario);
        
    }
    @Override
    public List<Medico> listar() {
        return repo.findAll();   
    }
}