package com.clinicacibertec.service;

import com.clinicacibertec.model.Reporte;
import com.clinicacibertec.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private ReporteRepository repo;

    @Override
    public List<Reporte> listar() {
        return repo.findAll();
    }

    @Override
    public Reporte guardar(Reporte reporte) {
        return repo.save(reporte);
    }

    @Override
    public Optional<Reporte> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
