package com.clinicacibertec.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinicacibertec.model.Cita;
import com.clinicacibertec.model.Medico;
import com.clinicacibertec.model.Paciente;
import com.clinicacibertec.repository.CitaRepository;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository repo;

    @Override
    public List<Cita> listar() {
        return repo.findAll();
    }

    @Override
    public Cita guardar(Cita cita) {
        return repo.save(cita);
    }

    @Override
    public Optional<Cita> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Cita> listarPorPaciente(Paciente paciente) {
        return repo.findByPaciente(paciente);
    }

    // 🔥 ESTE TE FALTABA
    @Override
    public List<Cita> listarPorMedico(Medico medico) {
        return repo.findByMedico(medico);
    }

    // 🔥 PARA DASHBOARD
    @Override
    public List<Cita> listarPorMedicoYFecha(Medico medico, LocalDate fecha) {
        return repo.findByMedicoAndFecha(medico, fecha);
    }
}