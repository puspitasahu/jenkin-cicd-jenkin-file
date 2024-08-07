package com.jenkin.jenkin_cicd_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JenkinCicdDemoApplication {

	@GetMapping("/greetings/{name}")
	public String  getMessage(@PathVariable String name){
		return "first Jenkin Application  "+ name;
	}

	public static void main(String[] args) {

		SpringApplication.run(JenkinCicdDemoApplication.class, args);
	}

}
