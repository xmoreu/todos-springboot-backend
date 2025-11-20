package com.todos.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		 Dotenv dotenv = Dotenv.configure()
                               .ignoreIfMissing()
                               .load();

        // Establece el perfil activo según variable de entorno
        String profile = dotenv.get("SPRING_PROFILES_ACTIVE", "prod");
        System.setProperty("spring.profiles.active", profile);
		SpringApplication.run(BackendApplication.class, args);
	}

}
