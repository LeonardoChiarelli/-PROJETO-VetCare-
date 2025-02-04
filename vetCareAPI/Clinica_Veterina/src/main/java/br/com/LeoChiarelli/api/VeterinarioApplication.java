package br.com.LeoChiarelli.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.LeoChiarelli.api")
@EnableJpaRepositories(basePackages = "br.com.LeoChiarelli.api.domain.repository")
public class VeterinarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeterinarioApplication.class, args);
	}

}
