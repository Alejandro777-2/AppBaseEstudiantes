package com.udla.arquitectura.demo.controller;

import com.udla.arquitectura.demo.model.Producto;
import com.udla.arquitectura.demo.service.ProductoService;
import org.springframework.cache.annotation.Cacheable; // <-- Importación necesaria
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API REST del catálogo.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Endpoint: GET /api/productos
    @GetMapping
    @Cacheable("productosCache") // Guarda la lista completa en caché
    public List<Producto> listar() {
        System.out.println("--> Obteniendo lista completa de productos desde el servicio...");
        return productoService.listarTodos();
    }

    // Endpoint: GET /api/productos/{id}
    @GetMapping("/{id}")
    @Cacheable(value = "productoCache", key = "#id") // Guarda el producto por su ID
    public Producto buscar(@PathVariable Long id) {
        System.out.println("--> Buscando producto por ID " + id + " desde el servicio...");
        return productoService.buscarPorId(id);
    }
}