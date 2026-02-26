package com.clinicacibertec.service;

import com.clinicacibertec.model.Pago;
import com.clinicacibertec.model.Cita;
import com.clinicacibertec.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago guardar(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Optional<Pago> buscarPorId(Integer id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Optional<Pago> buscarPorCita(Cita cita) {
        return pagoRepository.findByCita(cita);
    }

    @Override
    public void eliminar(Integer id) {
        pagoRepository.deleteById(id);
    }
}