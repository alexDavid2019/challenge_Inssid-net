package com.inssid.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inssid.dto.OrdenCompraResponseDto;
import com.inssid.dto.OrdenParcialRequestDto;
import com.inssid.dto.OrdenParcialResponseDto;
import com.inssid.entity.Cliente;
import com.inssid.entity.DetalleVenta;
import com.inssid.entity.Factura;
import com.inssid.entity.PreOrden;
import com.inssid.entity.Producto;
import com.inssid.entity.Venta;


@Service
public class OperacionesService {

	@Autowired
	private ClienteService clientService;
	
	@Autowired
	private ProductoService productService;
	
	@Autowired
	private OrdenService ordenService;
	
	@Autowired
	private FacturaService facturaService;
	
	public OrdenParcialResponseDto BuscarOrden(Integer id) throws Exception {
		PreOrden _model = ordenService.BuscarPreOrdenById(id);
		if (_model != null)
			return new OrdenParcialResponseDto(_model);
		else
			return null;
	}
	
	public OrdenCompraResponseDto FinalizarOrden(Integer id) throws Exception {
		PreOrden preOrden = ordenService.BuscarPreOrdenById(id);
		if (preOrden != null) {
			Factura factura = facturaService.BuscarFacturaByOrden(preOrden);
			if (factura == null) {
				//Creamos nueva factura..
				factura = new Factura(preOrden);
				int length = 20;
			    boolean useLetters = true;
			    boolean useNumbers = true;
			    String barCode = RandomStringUtils.random(length, useLetters, useNumbers);
			    factura.setBarCode(barCode);
			    factura = facturaService.GuardarFactura(factura);
			    
			    //Inhabilitamos la orden preliminar.
			    preOrden.setActivo(false);
			    preOrden = ordenService.GuardarOrden(preOrden);
			    
			    for(DetalleVenta item:preOrden.getVenta().getDetalleVentas()) {
			    	Producto _producto = item.getProducto();
			    	if (_producto.getActivo()) {
			    		//No volvemos a disponibilizar el producto una vez facturado.
			    		_producto.setActivo(false);
			    		productService.GuardarProducto(_producto);
			    	}
				}
			}
			return new OrdenCompraResponseDto(factura);
		}
		else
			return null;
	}
	
	public List<OrdenCompraResponseDto> ListarFacturasByCliente(Integer dni){
		List<OrdenCompraResponseDto> _result = new ArrayList<OrdenCompraResponseDto>();
		Cliente _cliente = clientService.BuscarCliente(dni);
		if (_cliente != null) {
			List<Factura> _facturas = facturaService.ListFacturaByCliente(_cliente);
			if (_facturas != null) {
				for(Factura item:_facturas) {
					_result.add(new OrdenCompraResponseDto(item));
				}
			}
		}
		return _result;
	}
	
	public OrdenParcialResponseDto CancelarOrden(Integer id) throws Exception {
		PreOrden _model = ordenService.BuscarPreOrdenById(id);
		if (_model != null) {
			_model.setActivo(false);
			_model = ordenService.GuardarOrden(_model);
			return new OrdenParcialResponseDto(_model);
		}
		else
			return null;
	}
	
	
	public OrdenParcialResponseDto RegistrarOrden(OrdenParcialRequestDto ordenParcial) {
		
		Cliente _cliente =  null;
		if (!clientService.ExistsCliente(ordenParcial.getCliente().getDni())) {
			_cliente = clientService.Registrar(ordenParcial.getCliente());
		}
		else {
			_cliente = clientService.BuscarCliente(ordenParcial.getCliente().getDni());
		}
		
		Producto _producto =  null;
		if (!productService.ExistsProducto(ordenParcial.getProducto().getCodigo())) {
			_producto = productService.Registrar(ordenParcial.getProducto());
		}
		else {
			_producto = productService.BuscarProducto(ordenParcial.getProducto().getCodigo());
		}	
		
		Venta _venta = ordenService.BuscarVentaActivaByCliente(_cliente);
		DetalleVenta detalle = new DetalleVenta();
		List<DetalleVenta> detalleVentas = null;
		if (_venta != null) {
			detalleVentas = _venta.getDetalleVentas();
		}
		else {
			_venta = new Venta();
		}
		
		if (detalleVentas == null) {
			detalleVentas = new ArrayList<DetalleVenta>();
		}
		
		detalle.setCantidad(1);
		detalle.setPrecioUnit(_producto.getPrecio());
		detalle.setProducto(_producto);
		detalle.setTotal(_producto.getPrecio());
		detalle.setVenta(_venta);
		
		detalleVentas.add(detalle);
		
		_venta.setDetalleVentas(detalleVentas);
		
		_venta = ordenService.GuardarVenta(_venta);
		
		PreOrden _preOrden = ordenService.BuscarPreOrdenActivaByCliente(_cliente);
		
		if (_preOrden == null) {
			_preOrden = new PreOrden();
			_preOrden.setActivo(true);
		}
		
		_preOrden.setCliente(_cliente);
		
		Float subtotal = 0f;
		for(DetalleVenta item:_venta.getDetalleVentas()) {
			subtotal += item.getTotal();
		}
		
		_preOrden.setSubTotal(subtotal);
		_preOrden.setVenta(_venta);
		_preOrden = ordenService.GuardarOrden(_preOrden);
		
		return new OrdenParcialResponseDto(_preOrden);
		
	}
	
}
