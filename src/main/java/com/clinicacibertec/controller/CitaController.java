package com.clinicacibertec.controller;

import com.clinicacibertec.dto.PagoReporteDTO;
import com.clinicacibertec.model.*;
import com.clinicacibertec.repository.*;
import com.clinicacibertec.service.CitaService;
import com.clinicacibertec.service.PagoService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.format.DateTimeFormatter;
@Controller
@RequestMapping("/paciente/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private MedicoRepository medicoRepo;
    
    @Autowired
    private PagoService pagoService;

    
    // LISTAR MIS CITAS
    
    @GetMapping
    public String listar(Model model) {

        String correo = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Usuario> usuarioOpt = usuarioRepo.findByCorreo(correo);

        if (usuarioOpt.isEmpty()) {
            return "redirect:/login";
        }

        Optional<Paciente> pacienteOpt = pacienteRepo.findByUsuario(usuarioOpt.get());

        if (pacienteOpt.isEmpty()) {
            return "redirect:/login";
        }

        List<Cita> citas = citaService.listarPorPaciente(pacienteOpt.get());

        model.addAttribute("citas", citas);

        return "paciente/citas";
    }

  
    // FORM NUEVA CITA
 
    @GetMapping("/nueva")
    public String nueva(Model model) {

        model.addAttribute("cita", new Cita());
        model.addAttribute("medicos", medicoRepo.findAll());

        return "paciente/nueva_cita";
    }

 
    // GUARDAR CITA
 
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cita cita) {

        String correo = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Usuario> usuarioOpt = usuarioRepo.findByCorreo(correo);

        if (usuarioOpt.isEmpty()) {
            return "redirect:/login";
        }

        Optional<Paciente> pacienteOpt = pacienteRepo.findByUsuario(usuarioOpt.get());

        if (pacienteOpt.isEmpty()) {
            return "redirect:/login";
        }

        cita.setPaciente(pacienteOpt.get());
        cita.setEstado(EstadoCita.PROGRAMADA); 
        cita.setPagada(false);

        citaService.guardar(cita);

        return "redirect:/paciente/citas";
    }
    
 // Mostrar formulario de pago
    
    @GetMapping("/pagar/{id}")
    public String pagarForm(@PathVariable Integer id, Model model) {
        Optional<Cita> citaOpt = citaService.buscarPorId(id);
        if (citaOpt.isEmpty() || citaOpt.get().getPagada()) {
            return "redirect:/paciente/citas";
        }

        model.addAttribute("cita", citaOpt.get());
        model.addAttribute("metodos", MetodoPago.values());
        return "paciente/pagar_cita";
    }
    
 // Guardar pago y generar boleta
    
    @PostMapping("/pagar/{id}/guardar")
    public String guardarPago(@PathVariable Integer id,
                              @RequestParam MetodoPago metodo,
                              Model model) {

        Optional<Cita> citaOpt = citaService.buscarPorId(id);
        if (citaOpt.isEmpty()) return "redirect:/paciente/citas";

        Cita cita = citaOpt.get();

        if (!cita.getPagada()) {
            Pago pago = new Pago();
            pago.setCita(cita);
            pago.setMonto(100.0); 
            pago.setMetodo(metodo);
            pago.setFechaPago(LocalDateTime.now());

            pagoService.guardar(pago);

            cita.setPagada(true);
            citaService.guardar(cita);
        }

        // Obtener pago para boleta
        Pago pago = pagoService.buscarPorCita(cita).orElse(null);

        model.addAttribute("cita", cita);
        model.addAttribute("pago", pago);

        return "paciente/boleta_pago";
    }
////
    @GetMapping("/pagar/{id}/imprimir")
    public void imprimirBoleta(@PathVariable Integer id, HttpServletResponse response) throws Exception {
        // 1. Obtener Cita y Pago
        Cita cita = citaService.buscarPorId(id)
                        .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        Pago pago = pagoService.buscarPorCita(cita)
                        .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        // 2. Crear DTO
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        PagoReporteDTO dto = new PagoReporteDTO(
            cita.getPaciente().getUsuario().getNombre() + " " + cita.getPaciente().getUsuario().getApellido(),
            cita.getMedico().getUsuario().getNombre() + " " + cita.getMedico().getUsuario().getApellido(),
            pago.getFechaPago().format(formatter),  
            pago.getMonto(),
            pago.getMetodo().name()
        );

        // 3. Lista de DTOs
        List<PagoReporteDTO> datosReporte = Collections.singletonList(dto);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datosReporte);

        // 4. Compilar el reporte
        JasperReport jasperReport = JasperCompileManager.compileReport(
            new ClassPathResource("reports/boleta_pago.jrxml").getInputStream()
        );

        // 5. Llenar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        // 6. Exportar PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=boleta_pago.pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    } }