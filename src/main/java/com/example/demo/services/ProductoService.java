package com.example.demo.services;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.entities.Producto;
import com.example.demo.mappers.ProductoMapper;
import com.example.demo.repositories.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService {

    private final IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDTO> findAll() {
        return this.productoRepository.findAll()
                .stream()
                .map(ProductoMapper.INSTANCE::productoToProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO findById(Long id) {
        Producto producto = this.productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return ProductoMapper.INSTANCE.productoToProductoDTO(producto);
    }

    @Override
    public ProductoDTO create(ProductoDTO dto) {
        Producto producto = ProductoMapper.INSTANCE.productoDTOToProducto(dto);
        return ProductoMapper.INSTANCE.productoToProductoDTO(this.productoRepository.save(producto));
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO dto) {
        Producto producto = this.productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        ProductoMapper.INSTANCE.updateProductoFromDTO(dto, producto);
        return ProductoMapper.INSTANCE.productoToProductoDTO(this.productoRepository.save(producto));
    }

    @Override
    public void delete(Long id) {
        if (!this.productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        this.productoRepository.deleteById(id);
    }
}
