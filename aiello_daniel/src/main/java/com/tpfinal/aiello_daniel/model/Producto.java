package com.tpfinal.aiello_daniel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String descripcion;
  private double precio;
  private String categoria;
  private String imagen;
  private double stock;

  // metodo constructor
  public Producto(String nombre,String descripcion, double precio, String categoria, String imagen, double stock) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.categoria = categoria;
    this.imagen = imagen;
    this.stock = stock;

  }

  public Producto() {
  }
}

