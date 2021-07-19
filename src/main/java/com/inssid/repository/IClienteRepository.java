package com.inssid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inssid.entity.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	Cliente findByDni(Integer dni);
    boolean existsByDni(Integer dni);
}
