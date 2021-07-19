package com.inssid.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "factura")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "fechaCreacion", updatable = false)
	private LocalDateTime fechaCreacion;

	@Column(name = "total")
	private Float total;

	@Column(name = "barCode")
	private String barCode;

	@ManyToOne
	@JoinColumn(name = "preOrden_id")
	private PreOrden preOrden;

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

	public Factura() {}
	
	public Factura(PreOrden model) {
		this.preOrden = model;
		this.total = model.getSubTotal();
		this.cliente = model.getCliente();
		this.venta = model.getVenta();
	}
	
	public String getFechaCreacion() {
		String formato = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        return formateador.format(fechaCreacion);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public PreOrden getPreOrden() {
		return preOrden;
	}

	public void setPreOrden(PreOrden val) {
		this.preOrden = val;
	}
	
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String val) {
		this.barCode = val;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float val) {
		this.total = val;
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
