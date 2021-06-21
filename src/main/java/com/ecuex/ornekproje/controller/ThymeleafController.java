package com.ecuex.ornekproje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ThymeleafController {

    @GetMapping("saveCustomer")
    public String saveCustomer() {
        return "saveCustomer";
    }

    @GetMapping("updateCustomer")
    public String updateCustomer() {
        return "updateCustomer";
    }

    @GetMapping("registrationSuccess")
    public String registrationSuccess() {
        return "registrationSuccess";
    }

    @GetMapping("getCustomers")
    public String getCustomers() {
        return "getCustomers";
    }

    @GetMapping("redirection")
    public String redirection() {
        return "redirection";
    }
}