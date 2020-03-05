package es.eoi.mundobancario.service;

import static es.eoi.mundobancario.utils.Fechas.queDiaEsHoy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.repository.AmortizacionRepository;
@Service
public class AmortizacionServiceImpl implements AmortizacionService {

	@Autowired
	AmortizacionRepository repository;

	@Autowired
	MovimientoService movimientoService;
	@Autowired
	CuentaService cuentaService;
	
	@Override
	public Amortizacion getById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Amortizacion> getAll() {
		return repository.findAll();
	}

	@Override
	public boolean post(Amortizacion amortizacion) {
		repository.save(amortizacion);
		return true;
	}

	public boolean ejecutarAmortizacionesDiarias() {
		for(Amortizacion amortizacion : repository.findByFecha(queDiaEsHoy())) {
			Movimiento movamorti = new Movimiento();
			movamorti.setDescripcion("Amortización: " + amortizacion.getPrestamo().getDescripcion());
			movamorti.setFecha(queDiaEsHoy());
			movamorti.setImporte(amortizacion.getImporte());
			movamorti.setCuenta(amortizacion.getPrestamo().getCuenta());
			movimientoService.post(movamorti);
			Cuenta cuentamorti = amortizacion.getPrestamo().getCuenta();
			cuentamorti.setSaldo(cuentamorti.getSaldo() - amortizacion.getImporte());
			cuentaService.post(cuentamorti);
		}
		return true;
	};
}
