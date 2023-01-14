package com.example.basicauthentication4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {


    @GetMapping("/login")
    public String login(){
        return "login";
    }



}
