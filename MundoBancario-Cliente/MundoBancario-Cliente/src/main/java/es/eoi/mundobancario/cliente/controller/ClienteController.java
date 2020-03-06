package es.eoi.mundobancario.cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import es.eoi.mundobancario.cliente.dto.ClienteDto;

@Controller
public class ClienteController {

	public static ClienteDto getClientes() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<ClienteDto> responseEntity = restTemplate.getForEntity("http://localhost:8080",
				ClienteDto.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK)
			return responseEntity.getBody();
		throw new RuntimeException("The server didn't respond OK");
	}
}