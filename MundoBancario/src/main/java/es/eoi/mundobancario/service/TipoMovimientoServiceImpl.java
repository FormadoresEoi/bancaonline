package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.TiposMovimiento;
import es.eoi.mundobancario.repository.TipoMovimientoRepository;

@Service
public class TipoMovimientoServiceImpl implements TipoMovimientoService {

	@Autowired
	TipoMovimientoRepository tipomovirepo;

	@Override
	public List<TiposMovimiento> MostrarTiposMovimiento() {
		return tipomovirepo.findAll();
	}

	@Override
	public TiposMovimiento CrearTiposMovimiento(TiposMovimiento tipomovimiento) {
		return tipomovirepo.save(tipomovimiento);
	}

	@Override
	public Optional<TiposMovimiento> buscarTiposMovimiento(int id) {
		return tipomovirepo.findById(id);
	}

	@Override
	public TiposMovimiento updateTiposMovimiento(TiposMovimiento tipomovimiento) {
		return tipomovirepo.save(tipomovimiento);
	}

	@Override
	public void removeTiposMovimiento(int id) {
		tipomovirepo.deleteById(id);
	}

	@Override
	public TiposMovimiento findByTipo(String tipo) {
		return tipomovirepo.findByTipo(tipo);
	}

}