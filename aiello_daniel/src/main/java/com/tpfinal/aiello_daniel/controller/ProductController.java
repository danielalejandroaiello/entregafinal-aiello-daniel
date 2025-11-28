package com.tpfinal.aiello_daniel.controller;

import com.tpfinal.aiello_daniel.model.Producto;
import com.tpfinal.aiello_daniel.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  private ProductService service;



  public ProductController(ProductService service) {
    this.service = service;
  }



  @PostMapping("/productos")
  public Producto crearProducto(@RequestBody Producto producto) {
    return this.service.crearProducto(producto);
  }




  // GET /products?nombre="product"&precio=123
  @GetMapping("/productos")
  public List<Producto> listarProductos(
      @RequestParam(required = false, defaultValue = "") String nombre,
      @RequestParam(required = false, defaultValue = "0") Double precio) {
    return this.service.listarProductos(nombre, precio);
  }



  //@PatchMapping
  @PutMapping("/productos/{id}")
  public Producto editarProducto(@PathVariable Long id, @RequestBody Producto producto) {
    return this.service.editarNombreProducto(id, producto);
  }




  @DeleteMapping("/productos/{id}")
  public Producto borrarProducto(@PathVariable(name = "id") Long productId) {
    return this.service.borrarProducto(productId);
  }

}

