package es.eoi.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import es.eoi.app.dto.ClienteDto;
import es.eoi.app.dto.PrestamoDto;

@Controller
public class MenuController {
		
	public  Scanner scan=null;
	public  String idCliente="";	
	public  String seleccion="";	
	@Value("${bancaonline.server.url}")
	public String url;
	
	public void printMainMenu() {	
		
		
		
		System.out.println("¿Que operacion desea realizar?");			
		System.out.println("1 - Modificar información de usuario");
		System.out.println("2 - Consultar mis cuentas");
		System.out.println("3 - Gestionar/ solicitar prestamos");
		System.out.println("4 - amortizar prestamos");
		System.out.println("5 - Generacion de reportes");
		
		scan= new Scanner(System.in);
		seleccion= scan.next();
		RestTemplate restTemplate = new RestTemplate();
		/*
		do {
			System.out.println("¿Que operacion desea realizar?");			
			System.out.println("1 - Modificar información de usuario");
			System.out.println("2 - Consultar mis cuentas");
			System.out.println("3 - Gestionar/ solicitar prestamos");
			System.out.println("4 - amortizar prestamos");
			System.out.println("5 - Generacion de reportes");
			
			scan= new Scanner(System.in);
			message= scan.next();
			
		}while(message != "1" ||  message != "2" ||  message != "3" ||  message != "4" ||  message != "5"); 
			
			*/
		
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
		
			
		ResponseEntity prestamos;
		switch (seleccion) {
		case "1":
			System.out.println("introduzca el numero de usuario a modificar");
			idCliente= scan.next();
			
			System.out.println("introduzca el nuevo email");
			String email = scan.next();
			
			ClienteDto usuarioId = restTemplate.getForObject(url+"/clientes/" + idCliente, ClienteDto.class); 	 
			System.out.println(usuarioId.toString());
			
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
		
	
		
		 
		 
		
	
	}
	
}
