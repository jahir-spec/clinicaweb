package com.clinicacibertec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicacibertec.model.Medico;
import com.clinicacibertec.model.Usuario;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    Optional<Medico> findByUsuario(Usuario usuario);

}