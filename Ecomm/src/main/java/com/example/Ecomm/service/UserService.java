package com.example.Ecomm.service;

import com.example.Ecomm.model.User;
import com.example.Ecomm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User registerUser(User user) {
        try
        {
            User newUser = userRepo.save(user);
            System.out.println("User Added");
            return newUser;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public User loginUser(String email, String password) {
        //check if user is there or not
        User user = userRepo.findByEmail(email);
        if (user!=null && user.getPassword().equals(password))
        {
            return user;
        }
        return null;//invalid credentials
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
