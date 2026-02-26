package com.clinicacibertec.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.clinicacibertec.model.Cita;
import com.clinicacibertec.model.Medico;
import com.clinicacibertec.model.Paciente;

public interface CitaService {

	List<Cita> listar();
	Cita guardar(Cita cita);
	Optional<Cita> buscarPorId(Integer id);
	void eliminar(Integer id);
	List<Cita> listarPorPaciente(Paciente paciente);
	List<Cita> listarPorMedico(Medico medico);
	List<Cita> listarPorMedicoYFecha(Medico medico, LocalDate fecha);
}
