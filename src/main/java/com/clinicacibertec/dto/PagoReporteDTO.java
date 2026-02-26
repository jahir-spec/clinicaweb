package com.clinicacibertec.dto;

public class PagoReporteDTO {
    private String pacienteNombre;
    private String medicoNombre;
    private String fechaPago;  // <- String ahora
    private Double monto;
    private String metodoPago;

    public PagoReporteDTO() {}

    public PagoReporteDTO(String pacienteNombre, String medicoNombre, String fechaPago,
                          Double monto, String metodoPago) {
        this.pacienteNombre = pacienteNombre;
        this.medicoNombre = medicoNombre;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
    }

    // Getters y setters
    public String getPacienteNombre() { return pacienteNombre; }
    public void setPacienteNombre(String pacienteNombre) { this.pacienteNombre = pacienteNombre; }

    public String getMedicoNombre() { return medicoNombre; }
    public void setMedicoNombre(String medicoNombre) { this.medicoNombre = medicoNombre; }

    public String getFechaPago() { return fechaPago; }
    public void setFechaPago(String fechaPago) { this.fechaPago = fechaPago; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
}