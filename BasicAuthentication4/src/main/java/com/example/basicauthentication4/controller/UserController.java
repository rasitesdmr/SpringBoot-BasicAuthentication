package com.example.basicauthentication4.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @GetMapping("/homepage")
    @SecurityRequirement(name = "basic-auth")
    public ResponseEntity<String> homepage() {
        return new ResponseEntity<>("Ana Sayfaya Hoşgeldiniz", HttpStatus.OK);
    }


    @GetMapping("/dashboard")
    public String dashboard() {
        System.out.println();
        return "Dashboard Sayfasına Hoşgeldiniz";
    }

    @GetMapping("/authority")
    public ResponseEntity<String> authority() {
        return new ResponseEntity<>("Yetki Sayfasına Hoşgeldiniz", HttpStatus.OK);
    }

    @GetMapping("/signing")
    public String signing(){
        return "Test";
    }


}
