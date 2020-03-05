package es.eoi.mundobancario.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	MovimientoRepository movimientoRepository;
	
	@Autowired
	CuentaService cuentaService;
	
	@Override
	public void create(Movimiento movimiento) {
		movimientoRepository.save(movimiento);
	}

	@Override
	public Optional<Movimiento> findById(int id) {
		return movimientoRepository.findById(id);
	}

	@Override
	public List<Movimiento> findAll() {
		return movimientoRepository.findAll();
	}

	@Override
	public List<Movimiento> findAllByCuentaId(int id_cuenta) {
		return movimientoRepository.findAllByCuentaNumCuenta(id_cuenta);
	}
	
	@Override
	public void RealizarMovimiento(double importe, Cuenta cuenta, Timestamp fecha, 
								   String descripcion, TipoMovimiento tipo) {
		Movimiento movimiento = new Movimiento();
		movimiento.setDescripcion(descripcion);
		movimiento.setFecha(fecha);
		movimiento.setCuenta(cuenta);
		movimiento.setImporte(importe);
		movimiento.setTipo(tipo);
		this.create(movimiento);
		
		
		Cuenta cuentaMod = cuenta;
		if(tipo.getTipo().equals("Ingreso")) {
			cuentaMod.setSaldo(cuentaMod.getSaldo() + importe);
			cuentaService.update(cuentaMod);
		}
		else if(tipo.getTipo().equals("Pago")) {
			cuentaMod.setSaldo(cuentaMod.getSaldo() - importe);
			cuentaService.update(cuentaMod);
		}
		else if(tipo.getTipo().equals("Prestamo")) {
			cuentaMod.setSaldo(cuentaMod.getSaldo() + importe);
			cuentaService.update(cuentaMod);
		}
	}
}
