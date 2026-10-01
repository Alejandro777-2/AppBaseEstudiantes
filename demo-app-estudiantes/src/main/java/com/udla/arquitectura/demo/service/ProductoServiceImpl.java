package com.udla.arquitectura.demo.service;

import com.udla.arquitectura.demo.model.Producto;
import com.udla.arquitectura.demo.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Sujeto Real: Lógica de negocio y acceso al repositorio.
 */
@Service("productoServiceReal")
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listarTodos() {
        // Simulamos un retraso (por ejemplo, consulta lenta a la BD) para notar el beneficio del Proxy
        try {
            Thread.sleep(1500); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return productoRepository.listarTodos();
    }

    @Override
    public Producto buscarPorId(Long id) {
        return productoRepository.buscarPorId(id);
    }
}