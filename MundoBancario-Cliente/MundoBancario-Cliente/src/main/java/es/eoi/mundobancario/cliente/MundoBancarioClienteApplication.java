package es.eoi.mundobancario.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import es.eoi.mundobancario.cliente.controller.ClienteController;

@SpringBootApplication
@EnableAutoConfiguration
public class MundoBancarioClienteApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MundoBancarioClienteApplication.class, args);
		ClienteController controller = context.getBean(ClienteController.class);
		controller.getClientes();
		
	}

}
