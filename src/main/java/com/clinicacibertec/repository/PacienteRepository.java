package com.clinicacibertec.repository;

import com.clinicacibertec.model.Paciente;
import com.clinicacibertec.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Optional<Paciente> findByUsuario(Usuario usuario);
}
