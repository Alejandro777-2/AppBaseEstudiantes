package com.udla.arquitectura.demo.service;

import com.udla.arquitectura.demo.model.Producto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Proxy: Intercepta las llamadas para gestionar la caché local.
 */
@Service
@Primary // Hace que Spring inyecte el Proxy por defecto en el Controller
public class ProductoServiceProxy implements ProductoService {

    private final ProductoService productoServiceReal;
    
    // Almacenamiento local del Proxy (Caché)
    private List<Producto> cacheProductos = null;

    public ProductoServiceProxy(@Qualifier("productoServiceReal") ProductoService productoServiceReal) {
        this.productoServiceReal = productoServiceReal;
    }

    @Override
    public List<Producto> listarTodos() {
        if (cacheProductos == null) {
            System.out.println("--> [PROXY] Caché vacía. Obteniendo datos del Servicio Real...");
            cacheProductos = productoServiceReal.listarTodos();
        } else {
            System.out.println("--> [PROXY] Retornando respuesta directamente desde la caché del Proxy.");
        }
        return cacheProductos;
    }

    @Override
    public Producto buscarPorId(Long id) {
        // En este ejemplo pasa directo al servicio real (o puedes implementar caché por ID)
        return productoServiceReal.buscarPorId(id);
    }
}