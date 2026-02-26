package com.clinicacibertec.service;

import com.clinicacibertec.model.Paciente;
import com.clinicacibertec.model.Usuario;
import com.clinicacibertec.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repo;

    @Override
    public List<Paciente> listar() {
        return repo.findAll();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return repo.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Optional<Paciente> buscarPorUsuario(Usuario usuario) {
        return repo.findByUsuario(usuario);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
