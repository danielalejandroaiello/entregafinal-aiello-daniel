package com.tpfinal.aiello_daniel.service;

import com.tpfinal.aiello_daniel.model.Producto;
import com.tpfinal.aiello_daniel.repository.ProductoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

// beans
@Service
public class ProductService {

  //private ProductoMemRepository repository;
  private ProductoRepository productoRepository;

  public ProductService(ProductoRepository repository) {
    this.productoRepository = repository;
  }

  public Producto crearProducto(Producto producto) {
    System.out.println("Creando Nuevo Producto");

    return this.productoRepository.save(producto);
  }

  public List<Producto> listarProductos(String nombre, Double precio) {
    if (!nombre.isEmpty() && precio > 0) {
      return this.productoRepository.findByNombreContainingAndPrecioLessThanEqual(nombre, precio);
    }

    if (!nombre.isEmpty()) {
      return this.productoRepository.findByNombreContaining(nombre);
    }

    if (precio > 0) {
      return this.productoRepository.findByPrecioLessThanEqual(precio);
    }

    return this.productoRepository.findAll();
  }

  public Producto editarNombreProducto(Long id, Producto dataProducto) {

    // Validación de entrada
    if (dataProducto == null) {
      throw new IllegalArgumentException("Los datos del producto no pueden ser nulos.");
    }

    String nuevoNombre = dataProducto.getNombre();

    // Validaciones del nombre
    if (nuevoNombre == null || nuevoNombre.isBlank()) {
      throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
    }

    if (nuevoNombre.length() < 3) {
      throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres.");
    }

    if (nuevoNombre.length() > 50) {
      throw new IllegalArgumentException("El nombre no puede exceder los 50 caracteres.");
    }

    if (!nuevoNombre.matches("^[a-zA-Z0-9ÁÉÍÓÚáéíóúñÑ ]+$")) {
      throw new IllegalArgumentException("El nombre contiene caracteres no permitidos.");
    }

    Producto producto = this.productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No encontramos el producto con ID " + id));

    producto.setNombre(nuevoNombre);
    return this.productoRepository.save(producto);


  }

  public Producto borrarProducto(Long id) {
    // Validación del id
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("El ID del producto no es válido.");
    }

    Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

    try {
      productoRepository.delete(producto);
    } catch (Exception e) {
      throw new RuntimeException("Error al eliminar el producto con ID " + id, e);
    }

    System.out.println("Se borró correctamente el producto con id: " + producto.getId());
    return producto;
  }
}
