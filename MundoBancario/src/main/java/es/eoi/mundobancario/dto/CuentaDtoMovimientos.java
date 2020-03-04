package es.eoi.mundobancario.dto;

import java.util.List;

import es.eoi.mundobancario.entity.Movimiento;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Data
public class CuentaDtoMovimientos {

	private List<Movimiento> movimiento;

}
