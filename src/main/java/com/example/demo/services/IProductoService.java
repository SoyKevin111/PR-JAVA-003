package com.example.demo.services;

import com.example.demo.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> findAll();

    ProductoDTO findById(Long id);

    ProductoDTO create(ProductoDTO dto);

    ProductoDTO update(Long id, ProductoDTO dto);

    void delete(Long id);
}
