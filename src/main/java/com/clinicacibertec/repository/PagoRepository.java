package com.clinicacibertec.repository;

import com.clinicacibertec.model.Pago;
import com.clinicacibertec.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
    Optional<Pago> findByCita(Cita cita);
}