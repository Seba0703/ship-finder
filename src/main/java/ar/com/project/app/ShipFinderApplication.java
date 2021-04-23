package ar.com.project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan("ar.com.project")
public class ShipFinderApplication {

	public static void main(String[] args) {
		System.out.println("Arriba");
		SpringApplication.run(ShipFinderApplication.class, args);
	}

}
