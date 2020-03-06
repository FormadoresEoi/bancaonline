package es.eoi.cliente.ui;

import java.util.Scanner;

import es.eoi.cliente.service.AppService;

public class App {

	static AppService service = new AppService();
	
	static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String opcion;
		do {
			printMenuPrincipal();
			opcion = sc.nextLine();
			switch (opcion) {
			case "1":
				menuUsuario();
				break;
			case "2":
				menuCuentas();
				break;
			case "3":
				menuPrestamos();
				break;
			case "4":
				menuReportes();
				break;
			case "5":
				System.out.println("¡Hasta la vista!");
				break;
			default:
				System.out.println("La opción solicitada no existe.");
				break;
			}
		} while (!opcion.equals("5"));

	}
	public static void menuUsuario() {
		
	}
	public static void menuCuentas() {
		
	}
	public static void menuPrestamos() {
		
	}
	public static void menuReportes() {
		System.out.println("¿Qué reporte quieres generar?");
		System.out.println("1.- Reporte de cuentas");
		System.out.println("2.- Reporte de préstamos");
		service.generaReporte(sc.nextLine());		
	}
	public static void printMenuPrincipal() {
		System.out.println("*****************************************************");
		System.out.println("¡¡¡¡¡Benvenido a tu aplicación de Banca Flipante!!!!!");
		System.out.println("*****************************************************\n\n");
		System.out.println("¿Qué operación quieres realizar?\n");
		System.out.println("1.- Modificar información de usuario");
		System.out.println("2.- Consultar mis cuentas");
		System.out.println("3.- Gestionar/Solicitar préstamos");
		System.out.println("4.- Generación de reportes");
		System.out.println("5.- Salir de la aplicación, lo que es una pena, porque es la leche");
	}

}
