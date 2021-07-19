package com.inssid.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inssid.dto.ProductoDto;
import com.inssid.entity.Producto;
import com.inssid.repository.IProductoRepository;

@Service
public class ProductoService {

	 @Autowired
	 private IProductoRepository productRepository;

	 public Producto Registrar(ProductoDto datos)  {
    	Producto _new = new Producto(datos.getCodigo(),datos.getMarca(),datos.getModelo(), datos.getPrecio(), true);
    	return GuardarProducto(_new);
     }

	 public Producto GuardarProducto(Producto dato)  {
	    	return productRepository.save(dato);
	 }

	 public Producto BuscarProducto(String codigo)  {
    	return productRepository.findByCodigo(codigo);
     }

	 public Optional<Producto> getById(Integer id)  {
	    	return productRepository.findById(id);
     }

	 public Boolean ExistsProducto(String codigo) {
		 return productRepository.existsByCodigo(codigo);
	 }
	 
	  public List<Producto> ListarProductos() {
		  return productRepository.findAll(); 
	  }
	
}
