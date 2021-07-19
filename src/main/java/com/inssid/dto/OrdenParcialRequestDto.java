package com.inssid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenParcialRequestDto {

	@JsonProperty("cliente")
	private ClienteDto cliente;
	
	@JsonProperty("producto")
	private ProductoDto producto;

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto val) {
		this.cliente = val;
	}

	public ProductoDto getProducto() {
		return producto;
	}

	public void setProducto(ProductoDto val) {
		this.producto = val;
	}

}
