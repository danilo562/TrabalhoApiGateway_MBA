package br.com.impacta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Api_GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Api_GatewayApplication.class, args);
	}
}
