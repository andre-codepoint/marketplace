package com.frontend.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class CustomerUiController {
    @GetMapping
    public String goHome(final Model model) {
        return "home";
    }
}
