package com.example.demo.controllers;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.services.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(this.usuarioService.findAll());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTO created = this.usuarioService.create(dto);
        URI location = URI.create("/api/usuarios/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }
}
