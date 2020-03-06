package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	PrestamoRepository repository;

	@Override
	public Prestamo getById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public boolean post(Prestamo prestamo) {
		repository.save(prestamo);
		return true;
	}

	@Override
	public List<Prestamo> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Prestamo> getByCuenta(Cuenta cuenta) {
		return repository.findByCuenta(cuenta);
	}

	@Override
	public List<Prestamo> getPrestamosVivosByCuentaId(Integer idcuenta) {
		return repository.findByPrestamoVivoByCuentaId(idcuenta);
	}
	@Override
	public List<Prestamo> getPrestamosVivosAll() {
		return repository.findByPrestamoVivoAll();
	}
	@Override
	public List<Prestamo> getPrestamosAmortizados(Integer idcuenta){
		return repository.findByPrestamoAmortizadoByCuentaId(idcuenta);
	}
	@Override
	public List<Prestamo> getPrestamosAmortizados(){
		return repository.findByPrestamoAmortizadoAll();
	}
	
	@Override
	public List<Prestamo> getPrestamosRecienPagados(){
		return repository.findByPrestamoRecienPagado();
	}
	@Override
	public Optional<Prestamo> getByCuentaAndPagado(Cuenta cuenta, String pagado){
		return repository.findByCuentaAndPagado(cuenta, pagado);
	}

}
