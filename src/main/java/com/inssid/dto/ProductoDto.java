package com.inssid.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inssid.entity.Producto;

import lombok.Data;

@Data
public class ProductoDto {

	@NotBlank
    @NotEmpty
    @JsonProperty("codigo")
    private String codigo;

	@NotBlank
    @NotEmpty
    @JsonProperty("marca")
    private String marca;

	@NotBlank
    @NotEmpty
    @JsonProperty("modelo")
    private String modelo;

	@NotNull
    @JsonProperty("precio")
    private Float precio;
	
	public ProductoDto() {}
	
	public ProductoDto(Producto model) {
		this.codigo = model.getCodigo();
		this.marca = model.getMarca();
		this.modelo = model.getModelo();
		this.precio = model.getPrecio();
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String val) {
		this.codigo = val;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String val) {
		this.marca = val;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String val) {
		this.modelo = val;
	}


	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float val) {
		this.precio = val;
	}

}
