package es.eoi.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import es.eoi.app.dto.ClienteBasicoDto;
import es.eoi.app.dto.ClienteDto;

@Controller
public class MenuController {
		
	private final Scanner scan= new Scanner(System.in);
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${bancaonline.server.url}")
	public String url;
	
	public String printMainMenu() {	
		
		
		System.out.println("*************************************************");
		System.out.println("Bienvenido a tu aplicación de Banca Flipante!!!!!");
		System.out.println("*************************************************");
		System.out.println();
		System.out.println("¿Que operacion desea realizar?");
		System.out.println();
		System.out.println("1 - Modificar información de usuario");
		System.out.println("2 - Consultar mis cuentas");
		System.out.println("3 - Gestionar/ solicitar prestamos");
		System.out.println("4 - amortizar prestamos");
		System.out.println("5 - Generacion de reportes");
		System.out.println("6 - Exit");
		System.out.print("Opcion: ");
		System.out.println();
		
		return scan.nextLine();
	}
	
	public void menu() {
		
		
		boolean exit = false;
		String option = "";
		String idCliente = "";
		
		do {
			option = printMainMenu();
			switch (option) {
			case "1":
				updateCliente(idCliente);
				break;
				
			case "2":
				System.out.println("introduzca el numero de cuenta");
				idCliente= scan.nextLine();
				
				ClienteDto result = restTemplate.getForObject(url+"/clientes/" + idCliente + "/cuentas", ClienteDto.class); 	 
				System.out.println(result.toString());
				
				break;
			case "3":
				//get prestamos, create prestamos
				System.out.println("introduzca el numero de cuenta para consultar los prestamos");
				idCliente= scan.nextLine();		
				ResponseEntity prestamo = restTemplate.getForObject(url+"/cuentas/" + idCliente + "/prestamos",ResponseEntity.class); 	 
				System.out.println(prestamo.toString());
				
				break;
			case "4":
				
				HttpEntity<ClienteDto> requeste = new HttpEntity<>(new ClienteDto());
				ClienteDto clientee = restTemplate.postForObject(url + "/cuentas/ejecutarAmortizacionesDiarias", requeste, ClienteDto.class);
			
		
		break;
			case "5":

		break;
			default:
				break;
			}
		}while(!exit);
	}
	
	private void updateCliente(String idCliente) {
		ClienteBasicoDto clienteDto = null;
		
		System.out.println("Introduzca el id de usuario a modificar: ");
		idCliente= scan.nextLine();
		
		try {
			clienteDto = restTemplate.getForObject(url.concat("clientes/").concat(idCliente), ClienteBasicoDto.class);
			
		} catch (RestClientException e) {
			System.err.println(e.getMessage());
		}
		
		if(clienteDto == null) {			
			System.out.println("Cliente not found");
			return;
		}
		
		System.out.println("Introduzca el nuevo email(" + clienteDto.getEmail() + "): ");
		String email = scan.nextLine();
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("clientes/").concat(idCliente))
				.queryParam("email",email);

		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		HttpEntity<Map<String, String>> requestUpdate = new HttpEntity<Map<String,String>>(params);
		ResponseEntity<ClienteBasicoDto> response = restTemplate.exchange(
				builder.toUriString(), 
				HttpMethod.PUT, 
				requestUpdate, 
				ClienteBasicoDto.class, 
				params);
		
		if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("response received");
            System.out.println(response.getBody());
        } else {
            System.out.println("error occurred");
            System.out.println(response.getStatusCode());
        }
	}
	
}
