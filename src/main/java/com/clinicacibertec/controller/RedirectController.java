package com.clinicacibertec.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/redirect")
    public String redirectByRole(Authentication auth) {

        String rol = auth.getAuthorities().toString();

        if (rol.contains("ADMIN")) {
            return "redirect:/admin/dashboard";
        }
        if (rol.contains("MEDICO")) {
            return "redirect:/medico/dashboard";
        }
        if (rol.contains("PACIENTE")) {
            return "redirect:/paciente/dashboard";
        }

        return "redirect:/login";
    }
}