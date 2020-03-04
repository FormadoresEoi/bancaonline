package es.eoi.mundobancario.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.AmortizacionRepository;
import es.eoi.mundobancario.repository.MovimientoRepository;
import es.eoi.mundobancario.repository.PrestamoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	MovimientoRepository movimientoRepository;
	
	@Autowired
	PrestamoRepository prestamoRepository;
	
	@Autowired
	AmortizacionRepository amortizacionRepository;
	
	
	@Override
	public Movimiento FindById(int id) {
		return movimientoRepository.findById(id).get();
	}

	@Override
	public Movimiento createMovimiento(Movimiento movimiento) {
		return movimientoRepository.save(movimiento);
	}

	@Override
	public List<Movimiento> listMovimiento() {
		return movimientoRepository.findAll();
	}

	@Override
	public List<Movimiento> findByCuenta(int cuenta) {
		return movimientoRepository.findByCuenta(cuenta);
	}

	@Override
	public Movimiento createIngreso(float importe, int plazos) {
			Prestamo prestamo = new Prestamo();
			Date fecha = new Date();
			prestamo.setImporte(importe);
			prestamo.setPlazos(plazos);
			prestamo.setFecha(fecha);
			int mes = 1;
			Calendar calendar = Calendar.getInstance();			
			Amortizacion amortizacion = new Amortizacion();
			for (int i = 0; i < plazos; i++) {
				calendar.setTime(fecha);
				calendar.add(calendar.MONTH, mes);
				amortizacion.setImporte(importe / plazos);
				amortizacion.setFecha(calendar.getTime());
				amortizacionRepository.save(amortizacion);
				mes++;
			}
			
			prestamoRepository.save(prestamo);
		return null;
	}

}
