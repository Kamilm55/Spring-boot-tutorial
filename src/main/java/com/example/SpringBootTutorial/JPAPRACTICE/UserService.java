package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional // this is atomic (a unit of work) // if  error occurs rollback all operations in this method
    public void updateUserEmailById(String newEmail) {
        User user1 = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));

        userRepo.updateUserEmailById(user1.getId(), newEmail);


        String newName = "kamil1234";
        userRepo.updateUserNameById(user1.getId(), newName);

        // all are executed , if exception happens all should rollback
//        throw new  RuntimeException("Error occured");
    }
}
