package es.eoi.mundobancario.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClienteDtoCuentas;
import es.eoi.mundobancario.dto.ClienteDtoCuentasMovimientos;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.CuentaDtoMovimientos;
import es.eoi.mundobancario.dto.CuentaDtoPrestamos;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.dto.PrestamoDtoAmortizaciones;
import es.eoi.mundobancario.dto.PrestamoDtoCliente;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;

@Component
public class DtoConstructor {
	
	@Autowired
	ModelMapper modelMapper;
	
	public CuentaDto toCuentaDto(Cuenta cuenta) {
		CuentaDto cuentaDto = modelMapper.map(cuenta, CuentaDto.class);
		return cuentaDto;
	}
	
	public CuentaDtoMovimientos toCuentaDtoMovimientos(Cuenta cuenta) {
		CuentaDtoMovimientos cuentaDto = modelMapper.map(cuenta, CuentaDtoMovimientos.class);
		return cuentaDto;
	}
	
	public CuentaDtoPrestamos toCuentaDtoPrestamos(Cuenta cuenta) {
		CuentaDtoPrestamos cuentaDto = modelMapper.map(cuenta, CuentaDtoPrestamos.class);
		return cuentaDto;
	}
	
	public MovimientoDto toMovimientoDto(Movimiento movimiento) {
		MovimientoDto movimientoDto = modelMapper.map(movimiento, MovimientoDto.class);
		return movimientoDto;
	}
	
	public PrestamoDto toPrestamoDto(Prestamo prestamo) {
		PrestamoDto prestamoDto = modelMapper.map(prestamo, PrestamoDto.class);
		return prestamoDto;
	}
	
	public PrestamoDtoCliente toPrestamoDtoCliente(Prestamo prestamo) {
		PrestamoDtoCliente prestamoDto = modelMapper.map(prestamo, PrestamoDtoCliente.class);
		return prestamoDto;
	}
	
	public PrestamoDtoAmortizaciones toPrestamoDtoAmortizaciones(Prestamo prestamo) {
		PrestamoDtoAmortizaciones prestamoDto = modelMapper.map(prestamo, PrestamoDtoAmortizaciones.class);
		return prestamoDto;
	}
	
	public ClienteDto toClienteDto(Cliente cliente) {
		ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);
		return clienteDto;
	}
	
	public ClienteDtoCuentas toClienteDtoCuentas(Cliente cliente) {
		ClienteDtoCuentas clienteDto = modelMapper.map(cliente, ClienteDtoCuentas.class);
		return clienteDto;
	}
	
	public ClienteDtoCuentasMovimientos ClienteDtoCuentasMovimientos(Cliente cliente) {
		ClienteDtoCuentasMovimientos clienteDto = modelMapper.map(cliente, ClienteDtoCuentasMovimientos.class);
		return clienteDto;
	}
}
