package com.udla.arquitectura.demo.service;

import com.udla.arquitectura.demo.model.Producto;
import java.util.List;

/**
 * Interfaz base para el Servicio y el Proxy.
 */
public interface ProductoService {
    List<Producto> listarTodos();
    Producto buscarPorId(Long id);
}