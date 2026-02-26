package com.clinicacibertec.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;

    private Double monto;

    private java.time.LocalDateTime fechaPago;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodo;


    public Pago() {}

    public Integer getIdPago() { return idPago; }
    public void setIdPago(Integer idPago) { this.idPago = idPago; }

    public Cita getCita() { return cita; }
    public void setCita(Cita cita) { this.cita = cita; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public java.time.LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(java.time.LocalDateTime fechaPago) { this.fechaPago = fechaPago; }

    public MetodoPago getMetodo() { return metodo; }
    public void setMetodo(MetodoPago metodo) { this.metodo = metodo; }
}
