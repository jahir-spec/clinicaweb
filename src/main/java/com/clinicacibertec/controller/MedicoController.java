package com.clinicacibertec.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.clinicacibertec.model.*;
import com.clinicacibertec.service.*;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private CitaService citaService;

    // DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {

        Usuario usuario = usuarioService.buscarPorCorreo(auth.getName()).get();
        Medico medico = medicoService.buscarPorUsuario(usuario).get();

        List<Cita> citas = citaService.listarPorMedico(medico);
        List<Cita> citasHoy = citaService.listarPorMedicoYFecha(medico, LocalDate.now());

        model.addAttribute("nombre",
                medico.getUsuario().getNombre() + " " + medico.getUsuario().getApellido());

        model.addAttribute("especialidad", medico.getEspecialidad().getNombre());

        model.addAttribute("totalCitas", citas.size());
        model.addAttribute("citasHoy", citasHoy.size());

        return "medico/dashboard";
    }

    //  LISTAR CITAS
    @GetMapping("/citas")
    public String verCitas(Model model, Authentication auth) {

        Usuario usuario = usuarioService.buscarPorCorreo(auth.getName()).get();
        Medico medico = medicoService.buscarPorUsuario(usuario).get();

        model.addAttribute("citas", citaService.listarPorMedico(medico));

        return "medico/citas";
    }

    //  ATENDER
    @GetMapping("/atender/{id}")
    public String atender(@PathVariable Integer id) {

        Cita cita = citaService.buscarPorId(id).get();
        cita.setEstado(EstadoCita.ATENDIDA);

        citaService.guardar(cita);

        return "redirect:/medico/citas";
    }

    //  CANCELAR
    @GetMapping("/cancelar/{id}")
    public String cancelar(@PathVariable Integer id) {

        Cita cita = citaService.buscarPorId(id).get();
        cita.setEstado(EstadoCita.CANCELADA);

        citaService.guardar(cita);

        return "redirect:/medico/citas";
    }
    
 //  CITAS DE HOY
    @GetMapping("/citas-hoy")
    public String verCitasHoy(Model model, Authentication auth) {

        Usuario usuario = usuarioService.buscarPorCorreo(auth.getName()).get();
        Medico medico = medicoService.buscarPorUsuario(usuario).get();

        model.addAttribute("citas",
                citaService.listarPorMedicoYFecha(medico, LocalDate.now()));

        return "medico/citas";
    }
    
 //  MI PERFIL
    @GetMapping("/perfil")
    public String verPerfil(Model model, Authentication auth) {

        Usuario usuario = usuarioService.buscarPorCorreo(auth.getName()).get();
        Medico medico = medicoService.buscarPorUsuario(usuario).get();

        model.addAttribute("medico", medico);

        return "medico/perfil";
    }
}