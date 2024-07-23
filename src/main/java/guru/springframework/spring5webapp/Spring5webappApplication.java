package guru.springframework.spring5webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring5webappApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Spring5webappApplication.class);
		app.setLazyInitialization(false);
		app.run(args);
	}

}
