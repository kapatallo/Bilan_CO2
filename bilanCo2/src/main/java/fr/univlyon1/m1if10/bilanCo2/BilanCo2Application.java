package fr.univlyon1.m1if10.bilanCo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * The type Bilan co 2 application.
 */
@SpringBootApplication
@Import(WebConfig.class)
public class BilanCo2Application {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(BilanCo2Application.class, args);
	}

}
