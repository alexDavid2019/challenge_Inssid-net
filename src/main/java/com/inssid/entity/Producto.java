package com.inssid.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "producto",uniqueConstraints = {@UniqueConstraint(columnNames = "codigo")})
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
    @NotEmpty
	@Column(name = "codigo")
	private String codigo;
	
	@NotBlank
    @NotEmpty
	@Column(name = "marca")
	private String marca;
	
	@NotBlank
    @NotEmpty
	@Column(name = "modelo")
	private String modelo;
	
	@NotNull
	@Column(name = "precio")
	private Float precio;
	
	@Column(name = "activo")
	private Boolean activo;
	
	@Column(name = "fechaCreacion", updatable = false)
	private LocalDateTime fechaCreacion;

	@PrePersist
	protected void onCreate() {
		this.fechaCreacion = LocalDateTime.now();
	}

	public Producto() {}
	
	public Producto(String codigo, String marca, String modelo, Float precio, Boolean activo) {
		this.codigo = codigo;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.activo = activo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean val) {
		this.activo = val;
	}
	

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Producto data = (Producto) o;
        return Objects.equals(codigo, data.getCodigo()) &&
                Objects.equals(modelo, data.getModelo()) && 
                Objects.equals(marca, data.getMarca()) && 
                Objects.equals(precio, data.getPrecio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, modelo, marca, precio);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }

}
