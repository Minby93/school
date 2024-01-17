package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class siteController {
    @GetMapping("welcome")
    public String welcome(){
        return "welcome.html";
    }

    @GetMapping("adminka")
    public String adminka(){
        return "adminka.html";
    }
}
