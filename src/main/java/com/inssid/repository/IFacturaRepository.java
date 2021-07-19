package com.inssid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inssid.entity.Cliente;
import com.inssid.entity.Factura;
import com.inssid.entity.PreOrden;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Integer> {

	@Query("select p from Factura p where p.cliente in (:client) order by p.fechaCreacion desc")
	List<Factura> getFacturas(@Param("client") Cliente client);
	
	@Query("select u from Factura u where u.preOrden = ?1")
	Factura findByPreOrden(PreOrden preOrden);
	
}
