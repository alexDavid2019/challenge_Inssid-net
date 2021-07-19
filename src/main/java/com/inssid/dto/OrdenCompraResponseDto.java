package com.inssid.dto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inssid.entity.DetalleVenta;
import com.inssid.entity.Factura;

public class OrdenCompraResponseDto {

	@JsonProperty("ordenNumero")
	private String ordenNumero;
	
	@JsonProperty("cliente")
	private ClienteDto cliente;
	
	@JsonProperty("total")
	private Float total;

	@JsonProperty("barCode")
	private String barCode;

	@JsonProperty("Fecha")
	private String fecha;

	@JsonProperty("Unidades")
	private Integer unidades;
	
	@Column(name = "productos")
	private List<ProductoDto> productos;

	public OrdenCompraResponseDto() {}
	
	public OrdenCompraResponseDto(Factura model) {
		this.ordenNumero = StringUtils.leftPad(model.getId().toString(), 8, "0");
		this.cliente = new ClienteDto(model.getCliente());
		this.productos = new ArrayList<ProductoDto>();
		this.total = model.getTotal();
		this.fecha = model.getFechaCreacion();
		this.barCode = model.getBarCode();
		if (model.getVenta() != null) 
		{
			this.unidades = model.getVenta().getDetalleVentas().size();
			for(DetalleVenta item:model.getVenta().getDetalleVentas()) 
			{
				this.productos.add(new ProductoDto(item.getProducto()));		
			}
		}		
	}


	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String val) {
		this.barCode = val;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer val) {
		this.unidades = val;
	}

	public String getOrdenNumero() {
		return ordenNumero;
	}

	public void setOrdenNumero(String val) {
		this.ordenNumero = val;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto val) {
		this.cliente = val;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float val) {
		this.total = val;
	}

	public List<ProductoDto> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoDto> val) {
		this.productos = val;
	}

}
