package com.ecommerce.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/admin")
    public ResponseEntity<String> homeAdmin() {
        return new ResponseEntity<>("Hello Admin!", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> homeUser() {
        return new ResponseEntity<>("Hello User!", HttpStatus.OK);
    }
}
