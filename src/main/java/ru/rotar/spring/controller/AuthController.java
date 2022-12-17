package ru.rotar.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("security/login");
        return model;
    }
}
