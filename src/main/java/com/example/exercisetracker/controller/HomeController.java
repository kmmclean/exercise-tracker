package com.example.exercisetracker.controller;

import com.example.exercisetracker.model.Role;
import com.example.exercisetracker.service.RoleService;
import com.example.exercisetracker.service.UserService;
import com.example.exercisetracker.util.transfer.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public HomeController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(name = "homeIndex")
    public String index() {
        return "home/index";
    }

    @GetMapping(path = "/about", name = "homeAbout")
    public String about() { return "home/about"; }

    @GetMapping(path = "/register", name = "homeRegister")
    public String register(@ModelAttribute RegisterDTO registerDTO) { return "home/register"; }

    @PostMapping(path = "/register", name = "homeSignUp")
    public String signUp(@Valid @ModelAttribute RegisterDTO registerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "home/register";
        }

        List<Role> roles = Arrays.asList(this.roleService.getRole("ROLE_USER"));
        registerDTO.setRoles(roles);

        this.userService.saveUser(registerDTO);

        return "redirect:/";
    }

    @RequestMapping(path = "/login", name = "homeLogin")
    public String login() { return "home/login"; }
}
