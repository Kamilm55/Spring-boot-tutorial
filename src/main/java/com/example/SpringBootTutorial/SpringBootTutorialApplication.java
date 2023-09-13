package com.example.SpringBootTutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@Controller// is used to declare common web controllers which can return HTTP response
//@RestController //  is used to create controllers for REST APIs which can return JSON
public class SpringBootTutorialApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootTutorialApplication.class, args);
	}

}
