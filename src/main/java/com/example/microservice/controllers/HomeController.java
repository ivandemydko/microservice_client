package com.example.microservice.controllers;


import com.example.microservice.entity.User;
import com.example.microservice.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/home", "/"})
    public String getHomePage() {
        return "home";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam("username") String name,
                               @RequestParam("password") String password, Model model, HttpSession session) {
        User newUser = userService.registerNewUserAccount(name, password);
        session.setAttribute("username", newUser.getUsername());
        model.addAttribute("newUser", newUser);
        return "success-register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(HttpSession session) {
        String usernsme = userService.getUserFromSecurityContext().getUsername();
        session.setAttribute("username", usernsme);
        return "forward:/home";
    }

}
