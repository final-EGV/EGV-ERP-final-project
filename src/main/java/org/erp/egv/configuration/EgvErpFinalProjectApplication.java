package org.erp.egv.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.erp.egv")
@EntityScan(basePackages = "org.erp.egv")
public class EgvErpFinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgvErpFinalProjectApplication.class, args);
	}

}
