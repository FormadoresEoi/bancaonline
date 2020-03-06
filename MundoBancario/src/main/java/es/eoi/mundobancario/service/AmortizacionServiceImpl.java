package es.eoi.mundobancario.service;

import static es.eoi.mundobancario.utils.Fechas.queDiaEsHoy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.AmortizacionRepository;

@Service
public class AmortizacionServiceImpl implements AmortizacionService {

	@Autowired
	AmortizacionRepository repository;

	@Autowired
	MovimientoService movimientoService;
	@Autowired
	CuentaService cuentaService;
	@Autowired
	PrestamoService prestamoService;
	@Autowired
	TipoMovimientoService tipoService;

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
		for (Amortizacion amortizacion : repository.findByFechaAndPagado(queDiaEsHoy(), "PENDIENTE")) {
			Movimiento movamorti = new Movimiento();
			movamorti.setDescripcion("Amortización: " + amortizacion.getPrestamo().getDescripcion());
			movamorti.setFecha(queDiaEsHoy());
			movamorti.setImporte(amortizacion.getImporte());
			movamorti.setCuenta(amortizacion.getPrestamo().getCuenta());
			movamorti.setTipo(tipoService.getByTipo("AMORTIZACIÓN"));
			movimientoService.post(movamorti);
			Movimiento movinteres = new Movimiento();
			movinteres.setDescripcion("Interés: " + amortizacion.getPrestamo().getDescripcion());
			movinteres.setFecha(queDiaEsHoy());
			movinteres.setImporte(amortizacion.getImporte() * 0.02f);
			movinteres.setCuenta(amortizacion.getPrestamo().getCuenta());
			movinteres.setTipo(tipoService.getByTipo("INTERÉS"));
			movimientoService.post(movinteres);
			Cuenta cuentamorti = amortizacion.getPrestamo().getCuenta();
			cuentamorti.setSaldo(cuentamorti.getSaldo() - amortizacion.getImporte());
			cuentamorti.setSaldo(cuentamorti.getSaldo() - amortizacion.getImporte() * 0.02f);
			cuentaService.post(cuentamorti);
			amortizacion.setPagado("PAGADO");
			repository.save(amortizacion);
		}
		List<Prestamo> prestamos = prestamoService.getPrestamosRecienPagados();
		for (Prestamo prestamo : prestamos) {
			prestamo.setPagado("PAGADO");
			prestamoService.post(prestamo);
		}
		return true;
	};
}
