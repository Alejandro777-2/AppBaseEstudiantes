package com.udla.arquitectura.demo.controller;

import com.udla.arquitectura.demo.model.Producto;
import com.udla.arquitectura.demo.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController         
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public Map<String, Object> listarAPI() {
        long inicio = System.currentTimeMillis();
        List<Producto> lista = productoService.listarTodos();
        long fin = System.currentTimeMillis();
        long tiempoTotal = fin - inicio;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("productos", lista);
        respuesta.put("tiempoMs", tiempoTotal);
        respuesta.put("usosCache", tiempoTotal < 200);
        return respuesta;
    }

    @GetMapping("/{id}")
    public Producto buscar(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }
}