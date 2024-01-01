package com.example.SpringBootTutorial.basicauth.repository;

import com.example.SpringBootTutorial.basicauth.model.Role;
import com.example.SpringBootTutorial.basicauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);
    @Query("select authorities from User where username = :username")
    Set<Role> getRoleOfUserByUsername(String username);
}
