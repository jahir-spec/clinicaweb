package com.clinicacibertec.repository;

import com.clinicacibertec.model.Cita;
import com.clinicacibertec.model.Paciente;
import com.clinicacibertec.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

	List<Cita> findByPaciente(Paciente paciente);

	List<Cita> findByMedico(Medico medico);

	List<Cita> findByMedicoAndFecha(Medico medico, LocalDate fecha);
	}
