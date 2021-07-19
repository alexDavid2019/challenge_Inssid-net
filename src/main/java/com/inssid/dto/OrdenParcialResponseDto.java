package com.inssid.dto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inssid.entity.DetalleVenta;
import com.inssid.entity.PreOrden;
import lombok.Data;

@Data
public class OrdenParcialResponseDto {

	@JsonProperty("ordenNumero")
	private String ordenNumero;
	
	@JsonProperty("cliente")
	private ClienteDto cliente;
	
	@JsonProperty("subTotal")
	private Float subTotal;
	
	@JsonProperty("Unidades")
	private Integer unidades;
	
	@Column(name = "productos")
	private List<ProductoDto> productos;

	public OrdenParcialResponseDto() {}
	
	public OrdenParcialResponseDto(PreOrden model) {
		this.ordenNumero = StringUtils.leftPad(model.getId().toString(), 8, "0");
		this.cliente = new ClienteDto(model.getCliente());
		this.productos = new ArrayList<ProductoDto>();
		this.subTotal = model.getSubTotal();
		if (model.getVenta() != null) {
			this.unidades = model.getVenta().getDetalleVentas().size();
			for(DetalleVenta item:model.getVenta().getDetalleVentas()) {
				this.productos.add(new ProductoDto(item.getProducto()));		
			}
		}		
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

	public Float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Float val) {
		this.subTotal = val;
	}

	public List<ProductoDto> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoDto> val) {
		this.productos = val;
	}
}
