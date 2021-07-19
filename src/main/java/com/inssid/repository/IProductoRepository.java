package com.inssid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inssid.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
    boolean existsByCodigo(String codigo);
	Producto findByCodigo(String codigo);
}
