package com.clinicacibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;   
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clinicacibertec.model.Especialidad;
import com.clinicacibertec.model.Medico;
import com.clinicacibertec.model.Rol;
import com.clinicacibertec.model.Usuario;
import com.clinicacibertec.service.CitaService;
import com.clinicacibertec.service.EspecialidadService;
import com.clinicacibertec.service.MedicoService;
import com.clinicacibertec.service.PacienteService;
import com.clinicacibertec.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private EspecialidadService especialidadService;
    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private CitaService citaService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalMedicos", medicoService.listar().size());
        model.addAttribute("totalPacientes", pacienteService.listar().size());
        model.addAttribute("totalCitas", citaService.listar().size());

        return "admin/dashboard";
    }

    @GetMapping("/medicos")
    public String verMedicos(Model model) {
        model.addAttribute("medicos", medicoService.listar());
        return "admin/medicos";
    }

    @GetMapping("/pacientes")
    public String verPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listar());
        return "admin/pacientes";
    }

    @GetMapping("/citas")
    public String verCitas(Model model) {
        model.addAttribute("citas", citaService.listar());
        return "admin/citas";
    }
    
    
   
    
    @GetMapping("/medicos/editar/{id}")
    public String editarMedico(@PathVariable Integer id, Model model) {

        Medico medico = medicoService.buscarPorId(id).get();

        model.addAttribute("medico", medico);

        return "admin/medico-form";
    }
    
    @GetMapping("/medicos/eliminar/{id}")
    public String eliminarMedico(@PathVariable Integer id) {

        medicoService.eliminar(id);

        return "redirect:/admin/medicos";
    }
    
    //////
    /// 
    /// 
    @GetMapping("/medicos/nuevo")
    public String nuevoMedico(Model model) {

        model.addAttribute("medico", new Medico());
        model.addAttribute("especialidades", especialidadService.listar());

        return "admin/medico-form";
    }
    
    @PostMapping("/medicos/guardar")
    public String guardarMedico(@ModelAttribute Medico medico,
                                @RequestParam Integer idEspecialidad) {

        Usuario usuario = medico.getUsuario();

        usuario.setRol(Rol.MEDICO);
        usuario.setActivo(true);

        usuarioService.guardar(usuario);

        Especialidad esp = especialidadService
                .buscarPorId(idEspecialidad)
                .orElseThrow();

        medico.setEspecialidad(esp);
        medico.setActivo(true);

        medicoService.guardar(medico);

        return "redirect:/admin/medicos";
    }
}
