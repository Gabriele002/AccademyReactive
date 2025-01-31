package it.reactive.carSharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CarSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarSharingApplication.class, args);
	}

}
