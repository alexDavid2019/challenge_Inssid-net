package com.inssid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inssid.entity.Venta;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer>{

}
