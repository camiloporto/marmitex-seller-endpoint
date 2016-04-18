package br.com.camiloporto.marmitex.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MarmitexSellerEndpointApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarmitexSellerEndpointApplication.class, args);
	}
}
