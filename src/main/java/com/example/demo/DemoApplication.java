package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		URI dbUri = null;
		try {
			dbUri = new URI(System.getenv("DATABASE_URL"));
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
			System.out.println(dbUrl
            );
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
