package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	MovimientoRepository movirepo;

	
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

}