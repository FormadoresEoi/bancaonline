package es.eoi.mundobancario.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ClienteDTOPrint {
	
	private int id;
	private String usuario;
	private String nombre;
	private String email;
}
