package com.example.exercisetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String register() { return "home/register"; }

    @GetMapping(path = "/login", name = "homeLogin")
    public String login() { return "home/login"; }
}
