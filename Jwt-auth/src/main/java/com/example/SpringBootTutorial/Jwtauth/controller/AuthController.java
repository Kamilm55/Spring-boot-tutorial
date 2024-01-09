package com.example.SpringBootTutorial.Jwtauth.controller;

import com.example.SpringBootTutorial.Jwtauth.dto.CreateUserRequest;
import com.example.SpringBootTutorial.Jwtauth.dto.SearchUserDto;
import com.example.SpringBootTutorial.Jwtauth.model.User;
import com.example.SpringBootTutorial.Jwtauth.security.service.AccessTokenManager;
import com.example.SpringBootTutorial.Jwtauth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;
    private final AccessTokenManager accessTokenManager;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/signIn")
    public String signIn(@RequestBody SearchUserDto searchUserDto){

        try {
            authenticationManager.authenticate(

                    // authenticationManager calls authenticationprovider(DAO) it checks credentials for db

                    //Learn:
                    // This checks there is any user with this credentials or not
                    // this is my payload password:request.getPassword()
                    // and in config we set UserDetails which is fetched from db ,
                    // there is also my (userDetails) password associated with this username(email)
                    new UsernamePasswordAuthenticationToken(searchUserDto.username(), searchUserDto.password())
            );
        }catch (AuthenticationException ex){

            System.out.println("AUTHENTICATE process failed");

            log.error(ex.getClass().getSimpleName());
            log.error(ex.getMessage());

          throw new RuntimeException(ex.getMessage());

        }

        System.out.println("AUTHENTICATE process succeed");
        System.out.println("Credentials are true");

        User user = userService.getUserByUsername(searchUserDto.username());

        //Learn: We don't check password and username matches
        // it checks in Spring Security => authenticationManager , UsernamePasswordAuthenticationToken

        String token = accessTokenManager.generate(user);

        System.out.println(token);


        return token;
    }

//    @PostMapping("/signUp")
//    public String signUp(@RequestBody CreateUserRequest userRequest){
//
//
//        return token;
//    }

}
