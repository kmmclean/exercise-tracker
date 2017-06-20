package com.example.exercisetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class ReportController {
    @GetMapping(name = "reportIndex")
    public String index() { return "reports/index"; }
}
