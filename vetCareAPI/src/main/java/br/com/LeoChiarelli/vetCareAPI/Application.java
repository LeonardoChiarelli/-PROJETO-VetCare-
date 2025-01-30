package br.com.LeoChiarelli.vetCareAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.LeoChiarelli.vetCareAPI")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
