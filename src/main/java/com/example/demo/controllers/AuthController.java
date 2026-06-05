package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.AuthService;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.UsuarioDTO;

import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest rq) {
    String token = authService.login(rq.username(), rq.password());
    return ResponseEntity.ok().body(Map.of("token", token));
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody UsuarioDTO dto) {
    this.authService.register(dto);
    return ResponseEntity.ok().build();
  }

}
