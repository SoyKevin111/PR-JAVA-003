package com.example.demo.mappers;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "password", ignore = true)
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    // create
    @Mapping(target = "id", ignore = true)
    Usuario usuarioDTOToUsuario(UsuarioDTO dto);
}
