package com.inssid.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "preorden")
public class PreOrden {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "fechaCreacion", updatable = false)
	private LocalDateTime fechaCreacion;

	@Column(name = "subTotal")
	private Float subTotal;
	
	@Column(name = "activo")
	private Boolean activo;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "venta_id")
	private Venta venta;
	
	@PrePersist
	protected void onCreate() {
		this.fechaCreacion = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Float val) {
		this.subTotal = val;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean val) {
		this.activo = val;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente val) {
		this.cliente = val;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta val) {
		this.venta = val;
	}

}
