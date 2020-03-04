package es.eoi.mundobancario.dto;

import java.util.List;

import es.eoi.mundobancario.entity.Cuenta;
import lombok.Data;

@Data
public class ClienteReportDTO {
	
	private ClienteDTO cliente;
	private List<Cuenta> cuentas;
	private List<MovimientoDTO> movimientos;
	
}