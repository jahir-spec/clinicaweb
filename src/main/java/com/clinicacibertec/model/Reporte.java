package com.clinicacibertec.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReporte;

    @Enumerated(EnumType.STRING)
    private TipoReporte tipo;

    private java.time.LocalDateTime fechaGeneracion;

    @ManyToOne
    @JoinColumn(name = "generado_por")
    private Usuario generadoPor;

    public Reporte() {}

    public Integer getIdReporte() { return idReporte; }
    public void setIdReporte(Integer idReporte) { this.idReporte = idReporte; }

    public TipoReporte getTipo() { return tipo; }
    public void setTipo(TipoReporte tipo) { this.tipo = tipo; }

    public java.time.LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(java.time.LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }

    public Usuario getGeneradoPor() { return generadoPor; }
    public void setGeneradoPor(Usuario generadoPor) { this.generadoPor = generadoPor; }
}
