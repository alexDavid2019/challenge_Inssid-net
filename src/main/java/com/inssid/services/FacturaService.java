package com.inssid.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inssid.entity.Cliente;
import com.inssid.entity.Factura;
import com.inssid.entity.PreOrden;
import com.inssid.repository.IFacturaRepository;

@Service
public class FacturaService {
	@Autowired
	private IFacturaRepository facturaRepository;

	public Factura GuardarFactura(Factura model) {
		return facturaRepository.save(model);
	}

	public Factura BuscarFacturaById(Integer id) throws Exception  {
		Optional<Factura> _model = facturaRepository.findById(id);
		if (_model.isPresent()) {
			return _model.get();
		}
		throw new Exception("Factura ID no existe");
    }

	public List<Factura> ListFacturaByCliente(Cliente cliente)  {
		return  facturaRepository.getFacturas(cliente);
    }

	public Factura BuscarFacturaByOrden(PreOrden preOrden) throws Exception  {
		Factura _model = facturaRepository.findByPreOrden(preOrden);
		if (_model != null) {
			return _model;
		}
		return null;
    }

}
