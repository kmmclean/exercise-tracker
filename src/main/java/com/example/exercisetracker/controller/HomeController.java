package com.example.exercisetracker.controller;

import com.example.exercisetracker.exception.AccountExistsException;
import com.example.exercisetracker.model.Role;
import com.example.exercisetracker.model.User;
import com.example.exercisetracker.service.RoleService;
import com.example.exercisetracker.service.UserService;
import com.example.exercisetracker.util.transfer.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        try {
            this.userService.registerUser(registerDTO);
        } catch (AccountExistsException e) {
            bindingResult.rejectValue("email", "error.account_exists", e.getMessage());
            return "home/register";
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/login", name = "homeLogin")
    public String login() { return "home/login"; }

    @GetMapping(path = "/verify-account/{token}", name = "homeVerifyAccount")
    public String verifyAccount(@PathVariable UUID token, RedirectAttributes redirectAttributes) {
        Optional<User> user = this.userService.verifyUser(token);
        if (user.isPresent() && user.get().getVerified()) {
            redirectAttributes.addFlashAttribute("loginSuccess", "Your account was successfully verified.");
        } else {
            redirectAttributes.addFlashAttribute("loginFailure", "Account verification failed.");
        }
        return "redirect:/login";
    }
}
