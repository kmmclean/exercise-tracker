package com.example.exercisetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogController {
    @RequestMapping(name = "logIndex")
    public String index() { return "log/index"; }
}
