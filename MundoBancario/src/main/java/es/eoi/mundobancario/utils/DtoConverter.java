package es.eoi.mundobancario.utils;

import java.util.ArrayList;
import java.util.List;

import es.eoi.mundobancario.dto.AmortizacionDto;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClienteNuevoDto;
import es.eoi.mundobancario.dto.CuentaBasicaDto;
import es.eoi.mundobancario.dto.CuentaConClienteDto;
import es.eoi.mundobancario.dto.CuentaConMovimientosDto;
import es.eoi.mundobancario.dto.CuentaNuevaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.MovimientoNuevoDto;
import es.eoi.mundobancario.dto.PrestamoConClienteDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.dto.TipoMovimientoDto;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;

public class DtoConverter {
	
	// Bloque de Cliente
	static public ClienteDto toClienteDto(Cliente cliente) {
		ClienteDto dto = new ClienteDto();
		dto.setId(cliente.getId());
		dto.setUsuario(cliente.getUsuario());
		dto.setEmail(cliente.getEmail());
		dto.setNombre(cliente.getNombre());
		return dto;
	}
	

	

	static public List<ClienteDto> toClienteDtoList(List<Cliente> clientes) {
		List<ClienteDto> dtolist = new ArrayList<ClienteDto>();
		for (Cliente cliente : clientes)
			dtolist.add(toClienteDto(cliente));
		return dtolist;
	}

	static public Cliente fromClienteNuevoDto(ClienteNuevoDto dto) {
		Cliente cliente = new Cliente();
		cliente.setUsuario(dto.getUsuario());
		cliente.setPass(dto.getPass());
		cliente.setEmail(dto.getEmail());
		cliente.setNombre(dto.getNombre());
		return cliente;
	}

	// Bloque de Cuenta
	static public CuentaBasicaDto toCuentaBasicaDto(Cuenta cuenta) {
		CuentaBasicaDto dto = new CuentaBasicaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		return dto;
	}

	static public List<CuentaBasicaDto> toCuentaBasicaDtoList(List<Cuenta> cuentas) {
		List<CuentaBasicaDto> dtolist = new ArrayList<CuentaBasicaDto>();
		for (Cuenta cuenta : cuentas)
			dtolist.add(toCuentaBasicaDto(cuenta));
		return dtolist;
	}

	static public CuentaConClienteDto toCuentaConClienteDto(Cuenta cuenta) {
		CuentaConClienteDto dto = new CuentaConClienteDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		dto.setCliente(toClienteDto(cuenta.getCliente()));
		return dto;
	}

	static public List<CuentaConClienteDto> toCuentaConClienteDtoList(List<Cuenta> cuentas) {
		List<CuentaConClienteDto> dtolist = new ArrayList<CuentaConClienteDto>();
		for (Cuenta cuenta : cuentas)
			dtolist.add(toCuentaConClienteDto(cuenta));
		return dtolist;
	}
	
	static public CuentaConMovimientosDto toCuentaConMovimientosDto(Cuenta cuenta) {
		CuentaConMovimientosDto dto = new CuentaConMovimientosDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		dto.setMovimiento(toMovimientoDtoList(cuenta.getMovimientos()));
		return dto;

	}
	static public List<CuentaConMovimientosDto> toCuentaConMovimientosDtoList(List<Cuenta> cuentas){
		List<CuentaConMovimientosDto> dtolist = new ArrayList<CuentaConMovimientosDto>();
		for (Cuenta cuenta : cuentas)
			dtolist.add(toCuentaConMovimientosDto(cuenta));
		return dtolist;
	}
	
	
	static public Cuenta fromCuentaNuevaDto(CuentaNuevaDto dto) {
		Cuenta cuenta = new Cuenta();
		cuenta.setAlias(dto.getAlias());
		cuenta.setSaldo(dto.getSaldo());
		return cuenta;
	}

	// Bloque de Tipos de movimiento
	static public TipoMovimientoDto toTipoMovimientoDto(TipoMovimiento tipo) {
		TipoMovimientoDto dto = new TipoMovimientoDto();
		dto.setId(tipo.getId());
		dto.setTipo(tipo.getTipo());
		return dto;
	}

	// Bloque de movimientos
	static public Movimiento fromMovimientoNuevoDto(MovimientoNuevoDto dto) {
		Movimiento movimiento = new Movimiento();
		movimiento.setDescripcion(dto.getDescripcion());
		movimiento.setImporte(dto.getImporte());
		return movimiento;
	}

	static public List<MovimientoDto> toMovimientoDtoList(List<Movimiento> movimientos) {
		List<MovimientoDto> dtolist = new ArrayList<MovimientoDto>();
		for (Movimiento movimiento : movimientos)
			dtolist.add(toMovimientoDto(movimiento));
		return dtolist;
	}

	static public MovimientoDto toMovimientoDto(Movimiento movimiento) {
		MovimientoDto dto = new MovimientoDto();
		dto.setId(movimiento.getId());
		dto.setDescripcion(movimiento.getDescripcion());
		dto.setFecha(movimiento.getFecha());
		dto.setImporte(movimiento.getImporte());
		dto.setTipo(toTipoMovimientoDto(movimiento.getTipo()));
		return dto;
	}

	// Bloque de amortizaciones
	static public List<AmortizacionDto> toAmortizacionDtoList(List<Amortizacion> amortizaciones) {
		List<AmortizacionDto> dtoList = new ArrayList<AmortizacionDto>();
		for (Amortizacion amortizacion : amortizaciones)
			dtoList.add(toAmortizacionDto(amortizacion));
		return dtoList;
	}

	static public AmortizacionDto toAmortizacionDto(Amortizacion amortizacion) {
		AmortizacionDto dto = new AmortizacionDto();
		dto.setId(amortizacion.getId());
		dto.setFecha(amortizacion.getFecha());
		dto.setImporte(amortizacion.getImporte());
		return dto;
	}

	// Bloque de préstamos
	static public PrestamoDto toPrestamoDto(Prestamo prestamo) {
		PrestamoDto dto = new PrestamoDto();
		dto.setId(prestamo.getId());
		dto.setDescripcion(prestamo.getDescripcion());
		dto.setFecha(prestamo.getFecha());
		dto.setImporte(prestamo.getImporte());
		dto.setPlazos(prestamo.getPlazos());
		dto.setAmortizaciones(toAmortizacionDtoList(prestamo.getAmortizaciones()));
		return dto;
	}
	static public PrestamoConClienteDto toPrestamoConClienteDto(Prestamo prestamo) {
		PrestamoConClienteDto dto = new PrestamoConClienteDto();
		dto.setId(prestamo.getId());
		dto.setDescripcion(prestamo.getDescripcion());
		dto.setFecha(prestamo.getFecha());
		dto.setImporte(prestamo.getImporte());
		dto.setPlazos(prestamo.getPlazos());
		dto.setCliente(toClienteDto(prestamo.getCuenta().getCliente()));
		dto.setAmortizaciones(toAmortizacionDtoList(prestamo.getAmortizaciones()));
		return dto;
	}

	static public List<PrestamoDto> toPrestamoDtoList(List<Prestamo> prestamos) {
		List<PrestamoDto> dtoList = new ArrayList<PrestamoDto>();
		for (Prestamo prestamo : prestamos)
			dtoList.add(toPrestamoDto(prestamo));
		return dtoList;
	}

}
