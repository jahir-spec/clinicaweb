package com.clinicacibertec.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    private java.time.LocalDate fecha;
    private java.time.LocalTime hora;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private Boolean pagada;

    public Cita() {}

    public Integer getIdCita() { return idCita; }
    public void setIdCita(Integer idCita) { this.idCita = idCita; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public java.time.LocalDate getFecha() { return fecha; }
    public void setFecha(java.time.LocalDate fecha) { this.fecha = fecha; }

    public java.time.LocalTime getHora() { return hora; }
    public void setHora(java.time.LocalTime hora) { this.hora = hora; }

    public EstadoCita getEstado() { return estado; }
    public void setEstado(EstadoCita estado) { this.estado = estado; }

    public Boolean getPagada() { return pagada; }
    public void setPagada(Boolean pagada) { this.pagada = pagada; }
}
