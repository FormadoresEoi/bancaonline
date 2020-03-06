package es.eoi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.eoi.app.controller.MenuController;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
public class App {

		
	public static void main(String[] args) {		
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);		
		MenuController controller = context.getBean(MenuController.class);
		controller.printMainMenu();
	}
	
	
	
	
}
