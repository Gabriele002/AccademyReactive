package it.reactive.academy.computer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ComputerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ComputerApplication.class, args);
		ComputerRunner computerRunner = ctx.getBean(ComputerRunner.class);
		computerRunner.saluta();
	}

}
