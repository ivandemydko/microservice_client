package com.example.microservice.controllers;

import com.example.microservice.services.userprofileservice.RdbmService;
import com.example.microservice.services.userprofileservice.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    RdbmService rdbmService;

    @GetMapping("/userPage")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUserPage() {
        return "user-page";
    }


    @GetMapping(value = "/user/profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getUserProfile(HttpSession session) {
        UserProfile userProfile = rdbmService.getUserProfile();
        ModelAndView mv = new ModelAndView("user-profile");
        mv.addObject("userProfile", userProfile);
        return mv;
    }


}
