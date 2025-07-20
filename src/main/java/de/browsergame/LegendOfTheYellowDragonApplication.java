package de.browsergame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "de.browsergame.model.entity")
public class LegendOfTheYellowDragonApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegendOfTheYellowDragonApplication.class, args);
	}

}
