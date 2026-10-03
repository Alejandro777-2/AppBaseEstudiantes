package com.udla.arquitectura.demo.service;

import com.udla.arquitectura.demo.model.Producto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProductoServiceProxy implements ProductoService {

    private final ProductoService productoServiceReal;
    
    // Agrega 'static' para asegurar que la caché se mantenga en memoria entre peticiones HTTP
    private static List<Producto> cacheProductos = null;

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
        return productoServiceReal.buscarPorId(id);
    }
}