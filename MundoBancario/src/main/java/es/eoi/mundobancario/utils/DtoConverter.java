package es.eoi.mundobancario.utils;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClienteNuevoDto;
import es.eoi.mundobancario.dto.CuentaBasicaDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;

public class DtoConverter {

	// Bloque de Cliente
	static public ClienteDto toDto(Cliente cliente) {
		ClienteDto dto = new ClienteDto();
		dto.setId(cliente.getId());
		dto.setUsuario(cliente.getUsuario());
		dto.setEmail(cliente.getEmail());
		dto.setNombre(cliente.getNombre());
		return dto;
	}
	static public Cliente fromDto(ClienteNuevoDto dto) {
		Cliente cliente = new Cliente();
		cliente.setUsuario(dto.getUsuario());
		cliente.setPass(dto.getPass());
		cliente.setEmail(dto.getEmail());
		cliente.setNombre(dto.getNombre());
		return cliente;
	}

	// Bloque de Cuenta
	static public CuentaBasicaDto toDto(Cuenta cuenta) {
		CuentaBasicaDto dto = new CuentaBasicaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		return dto;
	}

}
