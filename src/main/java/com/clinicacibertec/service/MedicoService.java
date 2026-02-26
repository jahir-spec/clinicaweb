package com.clinicacibertec.service;

import java.util.List;
import java.util.Optional;

import com.clinicacibertec.model.Medico;
import com.clinicacibertec.model.Usuario;

public interface MedicoService {

    Optional<Medico> buscarPorUsuario(Usuario usuario);
    Medico guardar(Medico medico);

    Optional<Medico> buscarPorId(Integer id);

    void eliminar(Integer id);
    List<Medico> listar();
}