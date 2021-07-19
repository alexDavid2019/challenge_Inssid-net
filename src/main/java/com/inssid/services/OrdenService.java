package com.inssid.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inssid.entity.Cliente;
import com.inssid.entity.PreOrden;
import com.inssid.entity.Venta;
import com.inssid.repository.IPreOrdenRepository;
import com.inssid.repository.IVentaRepository;

@Service
public class OrdenService {

	@Autowired
	private IPreOrdenRepository preOrdenRepository;

	@Autowired
	private IVentaRepository ventaRepository;

	public Venta BuscarVentaActivaByCliente(Cliente cliente)  {
    	return preOrdenRepository.findVenta(cliente, true);
    }

	public PreOrden BuscarPreOrdenActivaByCliente(Cliente cliente)  {
    	return preOrdenRepository.findByCliente(cliente, true);
    }

	public PreOrden BuscarPreOrdenById(Integer id) throws Exception  {
		Optional<PreOrden> _model = preOrdenRepository.findById(id);
		if (_model.isPresent()) {
			PreOrden model = _model.get();
			return model.getActivo()? model: null;
		}
		throw new Exception("Orden no existe");
    }

	public Venta GuardarVenta(Venta model) {
		return ventaRepository.save(model);
	}

	public PreOrden GuardarOrden(PreOrden model) {
		return preOrdenRepository.save(model);
	}
}
