package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entities.AppUser;
import com.example.springsecurityjwt.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1")

public class UserController {
    private final UserService userService;
    @GetMapping("/default")
    public String message(){
        return "hello";
    }
    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUser(){

        return ResponseEntity.ok(userService.getAllUser());
    }
}



