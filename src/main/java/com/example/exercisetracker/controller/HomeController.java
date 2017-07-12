package com.example.exercisetracker.controller;

import com.example.exercisetracker.util.transfer.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping(name = "homeIndex")
    public String index() {
        return "home/index";
    }

    @GetMapping(path = "/about", name = "homeAbout")
    public String about() { return "home/about"; }

    @GetMapping(path = "/register", name = "homeRegister")
    public String register(@ModelAttribute RegisterForm registerForm) { return "home/register"; }

    @PostMapping(path = "/register", name = "homeSignUp")
    public String signUp(@Valid @ModelAttribute RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "home/register";
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/login", name = "homeLogin")
    public String login() { return "home/login"; }
}
