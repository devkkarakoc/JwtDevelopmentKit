package com.kkarakoc.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = {"com.kkarakoc"})
@EntityScan(basePackages = {"com.kkarakoc"})
@EnableJpaRepositories(basePackages = {"com.kkarakoc"})
@SpringBootApplication
public class JwtDevelopmentKitStarter {

	public static void main(String[] args) {
		SpringApplication.run(JwtDevelopmentKitStarter.class, args);
	}

}
