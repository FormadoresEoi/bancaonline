package es.eoi.cliente.ui;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		printMenuPrincipal();
		switch (sc.nextLine()) {
		case "1":
			
			break;
		case "2":
			
			break;
		case "3":
			
			break;
		case "4":
			
			break;
		case "5":
			
			break;
		default:
			System.out.println("La opción solicitada no existe.");
			break;
		}

	}

	
	public static void printMenuPrincipal() {
		System.out.println("*****************************************************");
		System.out.println("¡¡¡¡¡Benvenido a tu aplicación de Banca Flipante!!!!!");
		System.out.println("*****************************************************\n\n");
		System.out.println("¿Qué operación quieres realizar?\n");
		System.out.println("1.- Modificar información de usuario");
		System.out.println("2.- Consultar mis cuentas");
		System.out.println("3.- Gestionar/Solicitar préstamos");
		System.out.println("4.- Amortizar préstamos");
		System.out.println("5.- Generación de reportes");
		System.out.println("6.- Salir de la aplicación, lo que es una pena, porque es la leche");
	}

}
