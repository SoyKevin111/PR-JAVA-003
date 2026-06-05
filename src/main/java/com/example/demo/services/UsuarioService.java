package com.example.demo.services;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entities.Usuario;
import com.example.demo.mappers.UsuarioMapper;
import com.example.demo.repositories.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return this.usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper.INSTANCE::usuarioToUsuarioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO create(UsuarioDTO dto) {
        Usuario usuario = UsuarioMapper.INSTANCE.usuarioDTOToUsuario(dto);
        return UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(this.usuarioRepository.save(usuario));
    }

    @Override
    public List<UsuarioDTO> mayoresDeEdad() {
        return this.usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getEdad() > 18)
                .map(UsuarioMapper.INSTANCE::usuarioToUsuarioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> nombres() {
        return this.usuarioRepository.findAll()
                .stream()
                .map(Usuario::getNombre)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> nombresOrdenados() {
        return this.usuarioRepository.findAll()
                .stream()
                .map(Usuario::getNombre)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public long contarMayores() {
        return this.usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getEdad() > 18)
                .count();
    }
}
