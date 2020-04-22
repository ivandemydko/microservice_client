package com.example.microservice.controllers;


import com.example.microservice.services.AdminService;
import com.example.microservice.services.guestservice.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/adminPage")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminPage() {
        return "admin-page";
    }

    @GetMapping("/guests")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getGuests(Model model) {
        List<Guest> guests = adminService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests";
    }
}
