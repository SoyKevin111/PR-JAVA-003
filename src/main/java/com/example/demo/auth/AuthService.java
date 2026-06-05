package com.example.demo.auth;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entities.Usuario;
import com.example.demo.mappers.UsuarioMapper;
import com.example.demo.repositories.IUsuarioRepository;

@Service
public class AuthService {
  private final IUsuarioRepository usuarioRepository;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;

  public AuthService(IUsuarioRepository usuarioRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.jwtUtil = jwtUtil;
    this.passwordEncoder = passwordEncoder;
  }

  public void register(UsuarioDTO dto) {
    Usuario usuario = UsuarioMapper.INSTANCE.usuarioDTOToUsuario(dto);
    usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
    this.usuarioRepository.save(usuario);
  }

  public String login(String username, String password) {
    Usuario usuario = this.usuarioRepository
        .findByUsername(username)
        .orElseThrow(() -> new RuntimeException("usuario no encontradpo"));

    if (!passwordEncoder.matches(password, usuario.getPassword())) {
      throw new RuntimeException("contrasena invalida");
    }
    return jwtUtil.generateToken(usuario);
  }

}
