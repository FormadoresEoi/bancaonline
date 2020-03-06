package es.eoi.mundobancario.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.CuentasRepository;
import es.eoi.mundobancario.Repository.PrestamoRepository;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

@Service
public class PrestamoServiceImpl implements PrestamoService {
	@Autowired
	PrestamoRepository prestamoRepository;
	@Autowired
	CuentasRepository cuentasRepository;
	
	private Cuenta checkNull(Optional<Cuenta> cuenta) {
		try {
			if (cuenta.isPresent()) {
				return cuenta.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Prestamo create(Prestamo prestamo) {
		return prestamoRepository.save(prestamo);
	}

	public Optional<Prestamo> findById(int id) {
		return prestamoRepository.findById(id);
	}

	public Prestamo update(Prestamo prestamo) {
		return prestamoRepository.save(prestamo);
	}

	public void remove(int id) {
		prestamoRepository.deleteById(id);
	}

	public List<Prestamo> findAllVivos(int id) {
		List<Prestamo> prestamos = new ArrayList<Prestamo>();
		Cuenta cuenta = checkNull(cuentasRepository.findById(id));
		boolean pagado = false;
		for (Prestamo prestamo : cuenta.getPrestamo()) {
			for (Amortizacion amortizacion : prestamo.getAmortizacion()) {
				if (amortizacion.getFecha().getTime().compareTo(Calendar.getInstance().getTime()) > 0)
					pagado = true;
			}
			if(pagado) {
				prestamos.add(prestamo);
				pagado = false;
			}
		}
		return null;
	}

	public List<Prestamo> findAllAmortizados(int id) {
		List<Prestamo> prestamos = new ArrayList<Prestamo>();
		Cuenta cuenta = checkNull(cuentasRepository.findById(id));
		boolean pagado = false;
		for (Prestamo prestamo : cuenta.getPrestamo()) {
			for (Amortizacion amortizacion : prestamo.getAmortizacion()) {
				if (amortizacion.getFecha().getTime().compareTo(Calendar.getInstance().getTime()) <= 0)
					pagado = true;
			}
			if(pagado) {
				prestamos.add(prestamo);
				pagado = false;
			}
		}
		return null;
	}
}
