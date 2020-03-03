package es.eoi.mundobancario.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.CuentasRepository;
import es.eoi.mundobancario.Repository.MovimientoRepository;
import es.eoi.mundobancario.Repository.PrestamoRepository;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;

@Service
public class CuentaServiceImpl implements CuentaService {
	@Autowired
	CuentasRepository cuentasRepository;
	@Autowired
	MovimientoRepository movimientoRepository;
	@Autowired
	PrestamoRepository prestamoRepository;
	//TODO añadir excepcion
	private Cuenta checkNull(Optional<Cuenta> cuenta) {
		if(cuenta.isPresent()) {
			return cuenta.get();
		}else {
			return null;
		}
	}

	public Cuenta create(Cuenta cuenta) {
		return cuentasRepository.save(cuenta);
	}

	public Cuenta findById(int numCuenta) {
		return checkNull(cuentasRepository.findById(numCuenta));
	}

	public Cuenta update(Cuenta cuenta) {
		return cuentasRepository.save(cuenta);
	}

	public void remove(int numCuenta) {
		cuentasRepository.deleteById(numCuenta);
	}

	public Movimiento createPagos(Movimiento movimiento) {
		return movimientoRepository.save(movimiento);
	}
	//TODO añadir las amortizaciones
	public Prestamo createPrestamos(Prestamo prestamo, Movimiento movimiento) {
		return prestamoRepository.save(prestamo);
	}

	public Movimiento createIngresos(Movimiento movimiento) {
		return movimientoRepository.save(movimiento);
	}
	//TODO arreglar
	public Cuenta findPrestamosAmortizados(int id) {
		Cuenta cuenta = checkNull(cuentasRepository.findById(id));
		for (Prestamo prestamo : cuenta.getPrestamo()) {
			if(prestamo.getAmortizacion().size() == prestamo.getPlazo());
		}
		return cuenta;
	}
	//TODO arreglar
	public Cuenta findPrestamosVivos(int id) {
		Cuenta cuenta = checkNull(cuentasRepository.findById(id));
		for (Prestamo prestamo : cuenta.getPrestamo()) {
			if(prestamo.getAmortizacion().size() == prestamo.getPlazo());
		}
		return cuenta;
	}
	
	public List<Cuenta> findAllDeudora() {
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cuentasRepository.findAll()) {
			if(cuenta.getSaldo() < 0)
				cuentas.add(cuenta);
		}
		return cuentas;
	}

	public List<Cuenta> findAll() {
		return cuentasRepository.findAll();
	}
	
	public void ejecutarAmortizacionsDiarias() {
		// TODO Auto-generated method stub
	}
}
