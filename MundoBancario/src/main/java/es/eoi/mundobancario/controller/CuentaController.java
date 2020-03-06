package es.eoi.mundobancario.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.AmortizacionDTO;
import es.eoi.mundobancario.dto.CuentaDTOCreate;
import es.eoi.mundobancario.dto.CuentaDTOPrint;
import es.eoi.mundobancario.dto.MovimientoDTOCreate;
import es.eoi.mundobancario.dto.MovimientoDTOPrint;
import es.eoi.mundobancario.dto.PrestamoDTO;
import es.eoi.mundobancario.dto.PrestamoDTOAmort;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TiposMovimiento;
import es.eoi.mundobancario.service.AmortizacionService;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;
import es.eoi.mundobancario.service.PrestamoService;
import es.eoi.mundobancario.service.TipoMovimientoService;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentaController {

	@Autowired
	CuentaService cuentaserv;
	@Autowired
	ClienteService clientserv;

	@Autowired
	PrestamoService prestamoserv;

	@Autowired
	MovimientoService movserv;

	@Autowired
	PrestamoService preserv;

	@Autowired
	AmortizacionService amortserv;

	@Autowired
	TipoMovimientoService tipomovserv;

	@Autowired
	private ModelMapper modelmapper;

	@PostMapping
	public CuentaDTOPrint crearCuenta(@RequestBody CuentaDTOCreate dto) {
		Cuenta cuenta = modelmapper.map(dto, Cuenta.class);
		cuenta.setCliente(modelmapper.map(clientserv.buscarCliente(dto.getId_cliente()).get(), Cliente.class));
		return modelmapper.map(cuentaserv.InsertarCuenta(cuenta), CuentaDTOPrint.class);

	}

	@DeleteMapping(value = "/{id}")
	public void removeCuenta(@PathVariable(value = "id") int id) {

		cuentaserv.removeCuenta(id);

	}

	@GetMapping
	public List<CuentaDTOPrint> mostrarCuentas() {
		Type listType = new TypeToken<List<CuentaDTOPrint>>() {
		}.getType();
		List<Cuenta> cuentas = cuentaserv.MostrarCuenta();
		return modelmapper.map(cuentas, listType);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CuentaDTOPrint buscarCuenta(@PathVariable(value = "id") int id) {
		return modelmapper.map(cuentaserv.buscarCuenta(id).get(), CuentaDTOPrint.class);
	}

	@PostMapping(value = "/{id}/prestamos")
	public PrestamoDTO CrearPrestamo(@PathVariable int id, @RequestBody PrestamoDTO dto) {

		TiposMovimiento tipoMovimiento = tipomovserv.findByTipo("Prestamo");
		Cuenta cuenta = cuentaserv.buscarCuenta(id).get();
		Prestamo prestamo = insertarPrestamo(cuenta, dto);
		List<Amortizacion> listaAmortizacion = amortserv.calcularAmortizacion(prestamo);
		amortserv.CrearAmortizaciones(listaAmortizacion);
		movserv.crearMovimientoPrestamo(prestamo, cuenta, tipoMovimiento, dto.getDescripcion());
		cuentaserv.ActualizarSaldoPrestamo(prestamo, cuenta);
		return modelmapper.map(prestamo, PrestamoDTO.class);

	}

	private Prestamo insertarPrestamo(Cuenta cuenta, PrestamoDTO dto) {

		Prestamo prestamo = modelmapper.map(dto, Prestamo.class);
		prestamo.setCuenta(cuenta);

		return prestamoserv.CrearPrestamo(prestamo);
	}

	@PutMapping(value = "/update/{id}", params = { "alias" })
	public CuentaDTOPrint updateCuenta(@PathVariable(value = "id") int id,
			@RequestParam(value = "alias") String alias) {
		Cuenta cuenta = cuentaserv.buscarCuenta(id).get();
		cuenta.setAlias(alias);
		return modelmapper.map(cuentaserv.updateCuenta(cuenta), CuentaDTOPrint.class);

	}

	@GetMapping(value = "/deudoras")
	public List<CuentaDTOPrint> buscarCuentasDeudoras() {
		Type listType = new TypeToken<List<CuentaDTOPrint>>() {
		}.getType();
		List<Cuenta> cuentasdeudoras = cuentaserv.buscarCuentasDeudoras(0);
		return modelmapper.map(cuentasdeudoras, listType);
	}

	@GetMapping(value = "/{id}/movimientos")
	public List<MovimientoDTOPrint> buscarMovimientosCuenta(@PathVariable int id) {
		Type listType = new TypeToken<List<MovimientoDTOPrint>>() {
		}.getType();
		Cuenta cuenta = cuentaserv.buscarCuenta(id).get();
		return modelmapper.map(movserv.buscarMovimientosbyCuenta(cuenta), listType);
	}

	@GetMapping(value = "/{id}/prestamos")
	public List<PrestamoDTO> buscarPrestamos(@PathVariable int id) {
		Type listType = new TypeToken<List<PrestamoDTO>>() {
		}.getType();
		Cuenta cuenta = cuentaserv.buscarCuenta(id).get();
		List<Prestamo> prestamo = preserv.buscarPrestamosbyCuenta(cuenta);
		return modelmapper.map(prestamo, listType);

	}

	@GetMapping(value = "/{id}/prestamosVivos")
	public List<PrestamoDTOAmort> buscarPrestamosVivos(@PathVariable int id) {
		Type listType = new TypeToken<List<PrestamoDTOAmort>>() {
		}.getType();
		Type listTypeAmort = new TypeToken<List<AmortizacionDTO>>() {
		}.getType();
		List<Prestamo> prestamo = preserv.buscarPrestamosbyCuenta(cuentaserv.buscarCuenta(id).get());
		List<Prestamo> prestamosVivos = preserv.buscarPrestamosVivos(prestamo);
		List<PrestamoDTOAmort> prestamosVivosDTO = modelmapper.map(prestamosVivos, listType);
		for (int i = 0; i < prestamosVivosDTO.size(); i++) {
			List<Amortizacion> amortizaciones = amortserv.BuscarAmortizacionesByPrestamo(prestamosVivos.get(i));
			List<AmortizacionDTO> amortdto = modelmapper.map(amortizaciones, listTypeAmort);
			prestamosVivosDTO.get(i).setListAmort(amortdto);
		}
		return prestamosVivosDTO;

	}

	@GetMapping(value = "/{id}/prestamosAmortizados")
	public List<PrestamoDTOAmort> buscarPrestamosAmortizados(@PathVariable int id) {
		Type listType = new TypeToken<List<PrestamoDTOAmort>>() {
		}.getType();
		Type listTypeAmort = new TypeToken<List<AmortizacionDTO>>() {
		}.getType();
		List<Prestamo> prestamo = preserv.buscarPrestamosbyCuenta(cuentaserv.buscarCuenta(id).get());
		List<Prestamo> prestamosVivos = preserv.buscarPrestamosAmortizados(prestamo);
		List<PrestamoDTOAmort> prestamosVivosDTO = modelmapper.map(prestamosVivos, listType);
		for (int i = 0; i < prestamosVivosDTO.size(); i++) {
			List<Amortizacion> amortizaciones = amortserv.BuscarAmortizacionesByPrestamo(prestamosVivos.get(i));
			List<AmortizacionDTO> amortdto = modelmapper.map(amortizaciones, listTypeAmort);
			prestamosVivosDTO.get(i).setListAmort(amortdto);
		}
		return prestamosVivosDTO;

	}

	@PostMapping(value = "/{id}/ingresos")
	public MovimientoDTOPrint crearIngreso(@PathVariable int id, @RequestBody MovimientoDTOCreate ingresodto) {

		Movimiento ingreso = modelmapper.map(ingresodto, Movimiento.class);
		TiposMovimiento tipoingreso = tipomovserv.findByTipo("ingreso");
		Cuenta cuenta = cuentaserv.buscarCuenta(id).get();
		ingreso.setTiposmovimiento(tipoingreso);
		ingreso.setCuenta(cuenta);
		Movimiento result = movserv.CrearMovimiento(ingreso);
		cuenta.setSaldo(cuenta.getSaldo() + ingresodto.getImporte());
		cuentaserv.updateCuenta(cuenta);
		return modelmapper.map(result, MovimientoDTOPrint.class);
	}

	@PostMapping(value = "/{id}/pagos")
	public Object crearPagos(@PathVariable int id, @RequestBody MovimientoDTOCreate pagodto) {

		Movimiento ingreso = modelmapper.map(pagodto, Movimiento.class);
		TiposMovimiento tipoingreso = tipomovserv.findByTipo("pago");
		Cuenta cuenta = cuentaserv.buscarCuenta(id).get();
		if (cuenta.getSaldo() < 0)
			return "Pago cancelado. Cuenta en nuemros rojos";
		else {
			ingreso.setTiposmovimiento(tipoingreso);
			ingreso.setCuenta(cuenta);
			Movimiento mov = movserv.CrearMovimiento(ingreso);
			cuenta.setSaldo(cuenta.getSaldo() - pagodto.getImporte());
			cuentaserv.updateCuenta(cuenta);
			return modelmapper.map(mov, MovimientoDTOPrint.class);
		}

	}

}

//AllShiftM
