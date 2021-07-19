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
@Table(name = "cliente",uniqueConstraints = {@UniqueConstraint(columnNames = "dni")})
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "fechaCreacion", updatable = false)
	private LocalDateTime fechaCreacion;

	@NotBlank
    @NotEmpty
	@Column(name = "nombre")
	private String nombre;

	@NotBlank
    @NotEmpty
	@Column(name = "apellido")
	private String apellido;

	@NotNull
	@Column(name = "dni")
	private Integer dni;

	@NotBlank
    @NotEmpty
	@Column(name = "direccion")
	private String direccion;

	@Column(name = "telefono")
	private Integer telefono;

	@PrePersist
	protected void onCreate() {
		this.fechaCreacion = LocalDateTime.now();
	}

	public Cliente() {}
	
	public Cliente(String nombre, String apellido, Integer dni, String direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni=dni;
		this.direccion = direccion;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente data = (Cliente) o;
        return Objects.equals(nombre, data.getNombre()) &&
                Objects.equals(apellido, data.getApellido()) && 
                Objects.equals(dni, data.getDni()) && 
                Objects.equals(direccion, data.getDireccion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, dni, direccion);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

}
