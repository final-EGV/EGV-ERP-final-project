package org.erp.egv.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages = "org.erp.egv")
@EntityScan(basePackages = "org.erp.egv")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class EgvErpFinalProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EgvErpFinalProjectApplication.class, args);
	}
	
	/** for external Apache Tomcat war deploy */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EgvErpFinalProjectApplication.class);
	}

}
