package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TiposMovimiento;
import es.eoi.mundobancario.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	MovimientoRepository movirepo;

	@Autowired
	TipoMovimientoService tipoMovService;

	@Override
	public List<Movimiento> MostrarMovimiento() {
		return movirepo.findAll();
	}

	@Override
	public Movimiento CrearMovimiento(Movimiento movimiento) {
		return movirepo.save(movimiento);
	}

	@Override
	public Optional<Movimiento> buscarMovimiento(int id) {
		return movirepo.findById(id);
	}

	@Override
	public Movimiento updateMovimiento(Movimiento movimiento) {
		return movirepo.save(movimiento);
	}

	@Override
	public void removeMovimiento(int id) {
		movirepo.deleteById(id);

	}

	@Override
	public List<Movimiento> buscarMovimientosbyCuenta(Cuenta cuenta) {
		return movirepo.findAllByCuenta(cuenta);
	}

	@Override

	public Movimiento crearMovimientoAmortizacion(Amortizacion amortizacion, Cuenta cuenta,
			TiposMovimiento tipoMovimientos) {

		Movimiento movimientoAmor = new Movimiento();
		movimientoAmor.setDescripcion("Amortizacion Prestamo");
		movimientoAmor.setFecha(amortizacion.getFecha());
		movimientoAmor.setImporte(amortizacion.getImporte());
		movimientoAmor.setCuenta(cuenta);
		movimientoAmor.setTiposmovimiento(tipoMovimientos);
		return this.CrearMovimiento(movimientoAmor);

	}

	@Override

	public Movimiento crearMovimientoInteres(Amortizacion amortizacion, Cuenta cuenta,
			TiposMovimiento tipoMovimientos) {

		Movimiento movimientoInteres = new Movimiento();
		movimientoInteres.setDescripcion("Interes de la Amortizacion");
		movimientoInteres.setFecha(amortizacion.getFecha());
		movimientoInteres.setImporte((float) (amortizacion.getImporte()*0.02));
		movimientoInteres.setCuenta(cuenta);
		movimientoInteres.setTiposmovimiento(tipoMovimientos);
		
		
		return this.CrearMovimiento(movimientoInteres);

	}

	@Override
	public Movimiento crearMovimientoPrestamo(Prestamo prestamo, Cuenta cuenta, TiposMovimiento tipoMovimientos,
			String descripcion) {
		Movimiento movimientoPrest = new Movimiento();
		movimientoPrest.setDescripcion(descripcion);
		movimientoPrest.setCuenta(cuenta);
		movimientoPrest.setFecha(prestamo.getFecha());
		movimientoPrest.setImporte(prestamo.getImporte());
		movimientoPrest.setTiposmovimiento(tipoMovimientos);
		return this.CrearMovimiento(movimientoPrest);

	}

}