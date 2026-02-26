package com.clinicacibertec.service;

import com.clinicacibertec.model.Reporte;

import java.util.List;
import java.util.Optional;

public interface ReporteService {

    List<Reporte> listar();

    Reporte guardar(Reporte reporte);

    Optional<Reporte> buscarPorId(Integer id);

    void eliminar(Integer id);
}
