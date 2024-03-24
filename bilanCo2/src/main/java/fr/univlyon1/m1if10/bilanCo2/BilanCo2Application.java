package fr.univlyon1.m1if10.bilanCo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebConfig.class)
public class BilanCo2Application {

	public static void main(String[] args) {
		SpringApplication.run(BilanCo2Application.class, args);
	}

}
