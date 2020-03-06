package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.TiposMovimiento;

public interface TipoMovimientoService {

	List<TiposMovimiento> MostrarTiposMovimiento();

	TiposMovimiento CrearTiposMovimiento(TiposMovimiento tipomovimiento);

	Optional<TiposMovimiento> buscarTiposMovimiento(int id);

	TiposMovimiento updateTiposMovimiento(TiposMovimiento tipomovimiento);

	void removeTiposMovimiento(int id);

	public TiposMovimiento findByTipo(String tipo);

}