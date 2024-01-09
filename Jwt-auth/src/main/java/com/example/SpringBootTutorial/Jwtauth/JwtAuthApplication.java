package com.example.SpringBootTutorial.Jwtauth;

import com.example.SpringBootTutorial.Jwtauth.dto.CreateUserRequest;
import com.example.SpringBootTutorial.Jwtauth.model.Role;
import com.example.SpringBootTutorial.Jwtauth.model.User;
import com.example.SpringBootTutorial.Jwtauth.model.properties.security.SecurityProperties;
import com.example.SpringBootTutorial.Jwtauth.security.service.AccessTokenManager;
import com.example.SpringBootTutorial.Jwtauth.service.UserService;
import com.example.SpringBootTutorial.Jwtauth.util.PublicAndPrivateKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class JwtAuthApplication implements CommandLineRunner {
	private final UserService userService;
	private final SecurityProperties securityProperties;
	private final AccessTokenManager accessTokenManager;

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		createDummyData();
//		generateAndReadJwtToken();

	}
	private void  generateAndReadJwtToken(){
		User userAdmin = userService.getUserByUsername("adminAndTeacher");

		String token = accessTokenManager.generate(userAdmin);

		System.out.println(token);

		Long userId = Long.valueOf(
				accessTokenManager.read(token).get("sub", String.class)
		);

		System.out.println("Your user data: " + userService.getUserById(userId));
	}
	private  void createDummyData(){
		CreateUserRequest user1 = new CreateUserRequest("Kamil","Kamil_user","pass", Set.of(Role.ROLE_USER));
		userService.createUser(user1);

		CreateUserRequest teacher = new CreateUserRequest("teacher","teacher","pass", Set.of(Role.ROLE_TEACHER));
		userService.createUser(teacher);

		CreateUserRequest admin = new CreateUserRequest("admin","admin","pass", Set.of(Role.ROLE_ADMIN));
		userService.createUser(admin);

		CreateUserRequest adminAndTeacher = new CreateUserRequest("adminAndTeacher","adminAndTeacher","pass", Set.of(Role.ROLE_ADMIN,Role.ROLE_TEACHER));
		userService.createUser(adminAndTeacher);

		System.out.println(userService.getUserByUsername("Kamil_user"));
		System.out.println(userService.getUserByUsername("teacher"));
		System.out.println(userService.getUserByUsername("admin"));
		System.out.println(userService.getUserByUsername("adminAndTeacher"));

//		System.out.println(userRepository.getRoleOfUserByUsername("teacher"));
//		System.out.println(userRepository.getRoleOfUserByUsername("adminAndTeacher"));


//		System.out.println(Role.ROLE_USER);
//		System.out.println(Role.ROLE_USER.getValue());
//		System.out.println(Role.ROLE_USER.name());
		//Learn:
		// When you insert an enum into a database using a Spring Data JPA repository, the default behavior is to store the name of the enum constant in the database. The name() method, which is part of the Enum class, is used to retrieve the name of the enum constant.
	}
}
