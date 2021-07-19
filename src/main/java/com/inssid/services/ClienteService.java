package com.inssid.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inssid.dto.ClienteDto;
import com.inssid.entity.Cliente;
import com.inssid.repository.IClienteRepository;

@Service
public class ClienteService {

	  @Autowired
	  private IClienteRepository clientRepository;

	  public Cliente Registrar(ClienteDto datos) {
	    	Cliente _new = new Cliente(datos.getNombre(),datos.getApellido(), datos.getDni(), datos.getDireccion());
	    	_new.setTelefono(datos.getTelefono());
	    	return clientRepository.save(_new);
	  }

	  public Cliente BuscarCliente(Integer dni) {
		  return clientRepository.findByDni(dni); 
	  }
	  
	  public Boolean ExistsCliente(Integer dni) {
		  return clientRepository.existsByDni(dni); 
	  }
	  
	  public List<Cliente> ListarClientes() {
		  return clientRepository.findAll(); 
	  }
	
	  
}
