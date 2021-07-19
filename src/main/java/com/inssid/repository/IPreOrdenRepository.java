package com.inssid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inssid.entity.Cliente;
import com.inssid.entity.PreOrden;
import com.inssid.entity.Venta;

@Repository
public interface IPreOrdenRepository extends JpaRepository<PreOrden, Integer>{
	
	@Query("select u.venta from PreOrden u where u.cliente = ?1 and u.activo = ?2")
	Venta findVenta(Cliente cliente, Boolean activo);

	@Query("select u from PreOrden u where u.cliente = ?1 and u.activo = ?2")
	PreOrden findByCliente(Cliente cliente, Boolean activo);
	
}
