package com.example.SpringBootTutorial.basicauth;

import com.example.SpringBootTutorial.basicauth.dto.CreateUserRequest;
import com.example.SpringBootTutorial.basicauth.model.Role;
import com.example.SpringBootTutorial.basicauth.model.User;
import com.example.SpringBootTutorial.basicauth.repository.UserRepository;
import com.example.SpringBootTutorial.basicauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class BasicAuthApplication implements CommandLineRunner {
	private final UserService userService;
	private final UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(BasicAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createDummyData();
	}

	void createDummyData(){
		CreateUserRequest user1 = new CreateUserRequest("Kamil","Kamil_user","pass", Set.of(Role.ROLE_USER));
		userService.createUser(user1);

		CreateUserRequest teacher = new CreateUserRequest("teacher","teacher","pass", Set.of(Role.ROLE_TEACHER));
		userService.createUser(teacher);

		CreateUserRequest admin = new CreateUserRequest("admin","admin","pass", Set.of(Role.ROLE_ADMIN));
		userService.createUser(admin);

		CreateUserRequest adminAndTeacher = new CreateUserRequest("adminAndTeacher","adminAndTeacher","pass", Set.of(Role.ROLE_ADMIN,Role.ROLE_TEACHER));
		userService.createUser(adminAndTeacher);

//		System.out.println(userService.getUserByUsername("Kamil_user"));
//		System.out.println(userService.getUserByUsername("teacher"));
//		System.out.println(userService.getUserByUsername("admin"));
//		System.out.println(userService.getUserByUsername("adminAndTeacher"));

		System.out.println(userRepository.getRoleOfUserByUsername("teacher"));
		System.out.println(userRepository.getRoleOfUserByUsername("adminAndTeacher"));


	}
}
