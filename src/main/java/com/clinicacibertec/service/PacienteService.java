package com.clinicacibertec.service;

import java.util.List;
import java.util.Optional;
import com.clinicacibertec.model.Paciente;
import com.clinicacibertec.model.Usuario;

public interface PacienteService {

    List<Paciente> listar();

    Paciente guardar(Paciente paciente);

    Optional<Paciente> buscarPorId(Integer id);

    Optional<Paciente> buscarPorUsuario(Usuario usuario);

    void eliminar(Integer id);
}
