package com.inssid.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inssid.dto.GenericResponseDto;
import com.inssid.dto.OrdenCompraResponseDto;
import com.inssid.dto.OrdenParcialRequestDto;
import com.inssid.dto.OrdenParcialResponseDto;
import com.inssid.enums.Status;
import com.inssid.services.OperacionesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/operaciones")
@Slf4j
@Validated
public class OperacionesController {

	@Autowired
	private OperacionesService opeService;
	
	@CrossOrigin()
	@PostMapping("/GuardarOrdenParcial")
	public ResponseEntity<?> crearOrdenCompraParcialmente(@Valid @RequestBody OrdenParcialRequestDto ordenParcial) {
        
		if (ordenParcial == null || ordenParcial.getCliente() == null || ordenParcial.getProducto() == null) 
        {
		   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
					Status.NOT_FOUND,
					Arrays.asList("Argumentos invalidos"));
		   	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
        }
		
		try
    	{
			OrdenParcialResponseDto _result = opeService.RegistrarOrden(ordenParcial);
			GenericResponseDto<OrdenParcialResponseDto> _ret = 
					new GenericResponseDto<OrdenParcialResponseDto>(
							_result,Status.SUCCESS,null);
			 
    		return ResponseEntity.status(HttpStatus.OK).body(_ret);
		} catch (Exception ex) {
        	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
        										Status.FAILURE,
        										Arrays.asList(ex.getMessage()));
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
    	}
    }
	
	@CrossOrigin()
    @GetMapping(value = "/OrdenParcial/{numero}")
	public ResponseEntity<?> getOrdenParcialByNumero(@PathVariable(value = "numero")
		@NotNull
		@NotBlank
		final String numero) {
	    	    
		String regex = "\\d+";
		if (!numero.matches(regex)) 
        {
		   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
					Status.NOT_FOUND,
					Arrays.asList("Argumentos invalidos"));
		   	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
        }

		try {
			
			OrdenParcialResponseDto _result = opeService.BuscarOrden(Integer.valueOf(numero));
			
			if (_result != null ) {
				GenericResponseDto<OrdenParcialResponseDto> _ret = 
						new GenericResponseDto<OrdenParcialResponseDto>(
								_result,Status.SUCCESS,null);
	    		return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else { 
				GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
						Status.NOT_FOUND,
						Arrays.asList("Codigo de Orden no existe o no esta activo"));
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
    @GetMapping(value = "/CancelarOrden/{numero}")
	public ResponseEntity<?> cancelarOrdenParcialByNumero(@PathVariable(value = "numero")
		@NotNull
		@NotBlank
		final String numero) {
	    	    
		String regex = "\\d+";
		if (!numero.matches(regex)) 
        {
		   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
					Status.NOT_FOUND,
					Arrays.asList("Argumentos invalidos"));
		   	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
        }

		try {
			
			OrdenParcialResponseDto _result = opeService.CancelarOrden(Integer.valueOf(numero));
			
			if (_result != null ) {
				GenericResponseDto<OrdenParcialResponseDto> _ret = 
						new GenericResponseDto<OrdenParcialResponseDto>(
								_result,Status.SUCCESS,null);
	    		return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else { 
				GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
						Status.NOT_FOUND,
						Arrays.asList("Codigo de Orden no existe o no esta activo"));
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
    @GetMapping(value = "/FinalizarOrden/{numero}")
	public ResponseEntity<?> finalizarOrdenParcialByNumero(@PathVariable(value = "numero")
		@NotNull
		@NotBlank
		final String numero) {
	    	    
		String regex = "\\d+";
		if (!numero.matches(regex)) 
        {
		   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
					Status.NOT_FOUND,
					Arrays.asList("Argumentos invalidos"));
		   	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
        }

		try {
			
			OrdenCompraResponseDto _result = opeService.FinalizarOrden(Integer.valueOf(numero));
			
			if (_result != null ) {
				GenericResponseDto<OrdenCompraResponseDto> _ret = 
						new GenericResponseDto<OrdenCompraResponseDto>(
								_result,Status.SUCCESS,null);
	    		return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else { 
				GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
						Status.NOT_FOUND,
						Arrays.asList("Codigo de Orden no existe o no esta activo"));
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
    @GetMapping(value = "/ListarFacturasByCliente/{dni}")
	public ResponseEntity<?> getFacturasByDni(@PathVariable(value = "dni")
		@NotNull
		@NotBlank
		final String dni) {
	    	    
		String regex = "\\d+";
		if (!dni.matches(regex)) 
        {
		   	GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
					Status.NOT_FOUND,
					Arrays.asList("Argumentos invalidos"));
		   	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_ret);
        }

		try {
			
			List<OrdenCompraResponseDto> _results = opeService.ListarFacturasByCliente(Integer.valueOf(dni));
			
			if (_results != null ) {
				GenericResponseDto<List<OrdenCompraResponseDto>> _ret = 
						new GenericResponseDto<List<OrdenCompraResponseDto>>(
								_results,Status.SUCCESS,null);
	    		return ResponseEntity.status(HttpStatus.OK).body(_ret);
			}
			else { 
				GenericResponseDto<String> _ret = new GenericResponseDto<String>(Strings.EMPTY,
						Status.NOT_FOUND,
						Arrays.asList("DNI no posee facturas registradas"));
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
