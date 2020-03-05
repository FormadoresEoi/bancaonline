package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	PrestamoRepository presrepo;

	
	@Override
	public List<Prestamo> MostrarPrestamo() {
		return presrepo.findAll();
	}

	@Override
	public Prestamo CrearPrestamo(Prestamo prestamo) {
		return presrepo.save(prestamo);
	}

	@Override
	public Optional<Prestamo> buscarPrestamo(int id) {
		return presrepo.findById(id);
	}

	@Override
	public Prestamo updatePrestamo(Prestamo prestamo) {
		return presrepo.save(prestamo);
	}

	@Override
	public void removePrestamo(int id) {
		presrepo.deleteById(id);
		
	}
	
	@Override
	public List<Prestamo> buscarPrestamosbyCuenta(Cuenta cuenta) {
		return presrepo.findAllByCuenta(cuenta);
	}

	@Override
	public List<Prestamo> buscarprestamosVivos(List<Prestamo> pres) {
		List<Prestamo> presvivos=new ArrayList<Prestamo>();
		Calendar actualdate = Calendar.getInstance();
		Calendar auxdate= Calendar.getInstance();
		for (Prestamo prestamo : pres) {
			auxdate.setTime(prestamo.getFecha());
			auxdate.add(Calendar.MONTH, prestamo.getPlazos());
			if (actualdate.compareTo(auxdate)<0)
				presvivos.add(prestamo);
			
		}
		return presvivos;
	}



}