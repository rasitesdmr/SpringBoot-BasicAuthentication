package com.example.basicauthentication1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebController {

    @GetMapping("/homepage")
    public ResponseEntity<String> homepage(){
        return new ResponseEntity<>("Ana Sayfaya Hoşgeldiniz", HttpStatus.OK);
    }
    @GetMapping("/dashboard")
    public ResponseEntity<String>dashboard(){
        return new ResponseEntity<>("Dashboard Sayfasına Hoşgeldiniz",HttpStatus.OK);
    }
    @GetMapping("/authority")
    public ResponseEntity<String> authority(){
        return new ResponseEntity<>("Yetki Sayfasına Hoşgeldiniz",HttpStatus.OK);
    }
}
