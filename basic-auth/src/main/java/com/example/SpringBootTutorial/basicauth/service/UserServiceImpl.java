package com.example.SpringBootTutorial.basicauth.service;

import com.example.SpringBootTutorial.basicauth.dto.CreateUserRequest;
import com.example.SpringBootTutorial.basicauth.model.User;
import com.example.SpringBootTutorial.basicauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("No user with username " + username));
    }

    @Override
    public void createUser(CreateUserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.name())
                .username(userRequest.userName())
                .password(passwordEncoder.encode(userRequest.password()))
                .authorities(userRequest.authorities())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new RuntimeException("No user with id " + id));
    }


}
