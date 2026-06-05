package com.example.demo.services;

import com.example.demo.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {

    List<UsuarioDTO> findAll();

    UsuarioDTO create(UsuarioDTO dto);

    List<UsuarioDTO> mayoresDeEdad();

    List<String> nombres();

    List<String> nombresOrdenados();

    long contarMayores();
}
