package com.inssid.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inssid.entity.Cliente;

import lombok.Data;

@Data
public class ClienteDto {

	@NotBlank
    @NotEmpty
    @JsonProperty("nombre")
    private String nombre;

	@NotBlank
    @NotEmpty
    @JsonProperty("apellido")
    private String apellido;

	@NotBlank
    @NotEmpty
    @JsonProperty("direccion")
    private String direccion;

	@NotNull
    @JsonProperty("dni")
    private Integer dni;

    @JsonProperty("telefono")
    private Integer telefono;

    public ClienteDto() {}
    
    public ClienteDto(Cliente model) {
    	this.apellido = model.getApellido();
    	this.direccion = model.getDireccion();
    	this.dni = model.getDni();
    	this.nombre = model.getNombre();
    	this.telefono = model.getTelefono();
    }
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String val) {
		this.nombre = val;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String val) {
		this.apellido = val;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String val) {
		this.direccion = val;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer val) {
		this.dni = val;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer val) {
		this.telefono = val;
	}

}
