package com.clinicacibertec.service;

import com.clinicacibertec.model.Especialidad;
import com.clinicacibertec.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    private EspecialidadRepository repo;

    @Override
    public List<Especialidad> listar() {
        return repo.findAll();
    }

    @Override
    public Especialidad guardar(Especialidad especialidad) {
        return repo.save(especialidad);
    }

    @Override
    public Optional<Especialidad> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}