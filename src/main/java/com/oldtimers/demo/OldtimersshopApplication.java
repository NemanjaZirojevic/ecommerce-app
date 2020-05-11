package com.oldtimers.demo;


import com.oldtimers.demo.entity.User;
import com.oldtimers.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class OldtimersshopApplication {


	public static void main(String[] args) {
		SpringApplication.run(OldtimersshopApplication.class, args);

	}

}
