package com.clinicacibertec.service;

import com.clinicacibertec.model.Especialidad;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {

    List<Especialidad> listar();

    Especialidad guardar(Especialidad especialidad);

    Optional<Especialidad> buscarPorId(Integer id);

    void eliminar(Integer id);
}
