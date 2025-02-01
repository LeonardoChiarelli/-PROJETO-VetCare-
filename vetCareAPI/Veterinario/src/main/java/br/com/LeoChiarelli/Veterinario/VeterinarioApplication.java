package br.com.LeoChiarelli.Veterinario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.LeoChiarelli.Veterinario")
@EnableJpaRepositories(basePackages = "br.com.LeoChiarelli.Veterinario.domain.repository")
public class VeterinarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeterinarioApplication.class, args);
	}

}
