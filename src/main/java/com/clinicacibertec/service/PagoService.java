package com.clinicacibertec.service;

import com.clinicacibertec.model.Pago;
import com.clinicacibertec.model.Cita;

import java.util.List;
import java.util.Optional;

public interface PagoService {

    List<Pago> listar();

    Pago guardar(Pago pago);
    Optional<Pago> buscarPorId(Integer id);
    Optional<Pago> buscarPorCita(Cita cita);

    void eliminar(Integer id);
}