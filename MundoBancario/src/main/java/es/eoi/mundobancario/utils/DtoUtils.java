package es.eoi.mundobancario.utils;

import es.eoi.mundobancario.dto.AmortizacionDto;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.dto.TipoMovimientoDto;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;

public class DtoUtils {

	// To DTO methods

	static public AmortizacionDto toDto(Amortizacion amortizacion) {
		AmortizacionDto dto = new AmortizacionDto();
		dto.setId(amortizacion.getId());
		dto.setFecha(amortizacion.getFecha());
		dto.setImporte(amortizacion.getImporte());
		return dto;
	}

	static public ClienteDto toDto(Cliente cliente) {
		ClienteDto dto = new ClienteDto();
		dto.setId(cliente.getId());
		dto.setUsuario(cliente.getUsuario());
		dto.setNombre(cliente.getNombre());
		dto.setEmail(cliente.getEmail());
		return dto;
	}

	static public CuentaDto toDto(Cuenta cuenta) {
		CuentaDto dto = new CuentaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		return dto;
	}

	static public MovimientoDto toDto(Movimiento movimiento) {
		MovimientoDto dto = new MovimientoDto();
		dto.setId(movimiento.getId());
		dto.setDescripcion(movimiento.getDescripcion());
		dto.setFecha(movimiento.getFecha());
		dto.setImporte(movimiento.getImporte());
		return dto;
	}

	static public PrestamoDto toDto(Prestamo prestamo) {
		PrestamoDto dto = new PrestamoDto();
		dto.setId(prestamo.getId());
		dto.setDescrpicon(prestamo.getDescrpicon());
		dto.setFecha(prestamo.getFecha());
		dto.setImporte(prestamo.getImporte());
		dto.setPlazos(prestamo.getPlazos());
		return dto;
	}

	static public TipoMovimientoDto toDto(TipoMovimiento tipo) {
		TipoMovimientoDto dto = new TipoMovimientoDto();
		dto.setId(tipo.getId());
		dto.setTipo(tipo.getTipo());
		return dto;
	}

	// From DTO methods

	static public Amortizacion fromDto(AmortizacionDto dto) {
		Amortizacion amortizacion = new Amortizacion();
		amortizacion.setId(dto.getId());
		amortizacion.setFecha(dto.getFecha());
		amortizacion.setImporte(dto.getImporte());
		return amortizacion;
	}

	static public Cliente fromDto(ClienteDto dto) {
		Cliente cliente = new Cliente();
		cliente.setId(dto.getId());
		cliente.setUsuario(dto.getUsuario());
		cliente.setNombre(dto.getNombre());
		cliente.setEmail(dto.getEmail());
		return cliente;
	}

	static public Cuenta fromDto(CuentaDto dto) {
		Cuenta cuenta = new Cuenta();
		cuenta.setNum_cuenta(dto.getNum_cuenta());
		cuenta.setAlias(dto.getAlias());
		cuenta.setSaldo(dto.getSaldo());
		return cuenta;
	}

	static public Movimiento fromDto(MovimientoDto dto) {
		Movimiento movimiento = new Movimiento();
		movimiento.setId(dto.getId());
		movimiento.setDescripcion(dto.getDescripcion());
		movimiento.setFecha(dto.getFecha());
		movimiento.setImporte(dto.getImporte());
		return movimiento;
	}

	static public Prestamo fromDto(PrestamoDto dto) {
		return null;
	}

	static public TipoMovimiento fromDto(TipoMovimientoDto dto) {
		TipoMovimiento tipo = new TipoMovimiento();
		tipo.setId(dto.getId());
		tipo.setTipo(dto.getTipo());
		return null;
	}

}
