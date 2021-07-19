package com.inssid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inssid.dto.ClienteDto;
import com.inssid.dto.GenericResponseDto;
import com.inssid.dto.ProductoDto;
import com.inssid.entity.Cliente;
import com.inssid.entity.Producto;
import com.inssid.enums.Status;
import com.inssid.services.ClienteService;
import com.inssid.services.ProductoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/consultas")
@Slf4j
@Validated
public class ConsultasController {


	@Autowired
	private ClienteService clienteService;


	@Autowired
	private ProductoService productService;
	
	@CrossOrigin()
    @GetMapping(value = "/ListarClientes")
	public ResponseEntity<?> getListClientes() {

		try {
			
			List<Cliente> _list = clienteService.ListarClientes();
			
			if (_list != null ) {
				List<ClienteDto> _results = new ArrayList<ClienteDto>();
				for(Cliente item:_list) {
					_results.add(new ClienteDto(item));
				}
				GenericResponseDto<List<ClienteDto>> _ret = 
						new GenericResponseDto<List<ClienteDto>>(
								_results,Status.SUCCESS,null);
	    		return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else { 
				GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
						Status.NOT_FOUND,
						Arrays.asList("No existen clientes registrados"));
			   	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
			}
    	} catch (Exception ex) {
        	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
        										Status.FAILURE,
        										Arrays.asList(ex.getMessage()));
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
    	}

	}

	@CrossOrigin()
    @GetMapping(value = "/ListarProductos")
	public ResponseEntity<?> getListProductos() {

		try {
			
			List<Producto> _list = productService.ListarProductos();
			
			if (_list != null ) {
				List<ProductoDto> _results = new ArrayList<ProductoDto>();
				for(Producto item:_list) {
					_results.add(new ProductoDto(item));
				}
				GenericResponseDto<List<ProductoDto>> _ret = 
						new GenericResponseDto<List<ProductoDto>>(
								_results,Status.SUCCESS,null);
	    		return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else { 
				GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
						Status.NOT_FOUND,
						Arrays.asList("No existen productos registrados"));
			   	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
			}
    	} catch (Exception ex) {
        	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
        										Status.FAILURE,
        										Arrays.asList(ex.getMessage()));
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
    	}

	}
	
}
