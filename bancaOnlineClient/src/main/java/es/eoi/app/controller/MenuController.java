package es.eoi.app.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import es.eoi.app.dto.*;

@Controller
public class MenuController {
		
	public  Scanner scan= new Scanner(System.in);
	public  String idCliente="";	
	public  String seleccion="";	
	@Value("${bancaonline.server.url}")
	public String url;
	
	public String printMainMenu() {	
		
		
		System.out.println();
		System.out.println("--¿ Que operacion desea realizar ?--");			
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
		RestTemplate restTemplate = new RestTemplate();
		
		boolean exit = false;
		String option = "";
		
		do {
			option = printMainMenu();
			switch (seleccion) {
			case "1":
				System.out.println("Introduzca el id de usuario a modificar: ");
				idCliente= scan.next();
				
				System.out.println("Introduzca el nuevo email: ");
				String email = scan.next();
				
				ResponseEntity<ClienteBasicoDto> cliente = restTemplate.getForObject(url+"/clientes/" + idCliente, ResponseEntity.class);

				ClienteBasicoDto clienteDto = (ClienteBasicoDto) cliente.getBody();
				clienteDto.setEmail(email);
				HttpEntity<ClienteBasicoDto> requestUpdate = new HttpEntity<>(clienteDto);
				restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class);
				break;
				
			case "2":
				System.out.println("introduzca el numero de cuenta");
				idCliente= scan.next();
				
				ClienteDto result = restTemplate.getForObject(url+"/clientes/" + idCliente + "/cuentas", ClienteDto.class); 	 
				System.out.println(result.toString());
				
				break;
			case "3":
				//get prestamos, create prestamos
				System.out.println("introduzca el numero de cuenta para consultar los prestamos");
				idCliente= scan.next();		
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
		
		//hacer un post
		//HttpEntity<ClienteDto> request = new HttpEntity<>(new ClienteDto());
		//ClienteDto cliente = restTemplate.postForObject(url, request, ClienteDto.class);
		
		/*
		//headers
		HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
		
		
		//creo el cliente a modificar
		ClienteDto updatedInstance = new ClienteDto();
		
		updatedInstance.setEmail("mail");
		String resourceUrl = 
				url +"/clientes/" + idCliente;		
		
		HttpEntity<ClienteDto> requestUpdate = new HttpEntity<>(updatedInstance, headers);
		restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
		
		*/
	}
	
}
