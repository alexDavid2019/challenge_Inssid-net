package com.inssid.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "detalleVenta")
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "fechaCreacion", updatable = false)
	private LocalDateTime fechaCreacion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venta_id", nullable = false)
	private Venta venta;

	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column(name = "precio_unit", precision = 8)
	private Float precioUnit;
	
	@Column(name = "total", precision = 8)
	private Float total;

	@PrePersist
	protected void onCreate() {
		this.fechaCreacion = LocalDateTime.now();
	}
	
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer val) {
		this.cantidad = val;
	}

	public double getPrecioUnit() {
		return this.precioUnit;
	}

	public void setPrecioUnit(Float val) {
		this.precioUnit = val;
	}

	public Float getTotal() {
		return (cantidad * precioUnit);
	}

	public void setTotal(Float val) {
		this.total = val;
	}

}
