package com.tpfinal.aiello_daniel.repository;

import com.tpfinal.aiello_daniel.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  List<Producto> findByNombreContaining(String nombre);

  List<Producto> findByPrecioLessThanEqual(Double precio);

  List<Producto> findByNombreContainingAndPrecioLessThanEqual(String nombre, Double precio);
}