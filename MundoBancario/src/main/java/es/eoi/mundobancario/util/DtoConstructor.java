package es.eoi.mundobancario.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.CuentaDtoMovimientos;
import es.eoi.mundobancario.dto.CuentaDtoPrestamos;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;

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
}
