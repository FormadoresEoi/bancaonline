package es.eoi.mundobancario.service;

import org.springframework.beans.factory.annotation.Autowired;

import es.eoi.mundobancario.repository.CuentaRepository;



public class CuentaServiceImpl implements CuentaService {
	@Autowired
	CuentaRepository repository;
}