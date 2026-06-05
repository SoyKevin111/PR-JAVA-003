package com.example.demo.mappers;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.entities.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    ProductoDTO productoToProductoDTO(Producto producto);

    @Mapping(target = "id", ignore = true)
    Producto productoDTOToProducto(ProductoDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateProductoFromDTO(ProductoDTO dto, @MappingTarget Producto producto);
}
