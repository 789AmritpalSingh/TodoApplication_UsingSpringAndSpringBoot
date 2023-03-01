package com.example.mywebapp.login;


import org.springframework.stereotype.Service;

@Service // for business logic -- created this bean to autowire to constructor of loginController
public class AuthenticationService {
    public boolean authenticate(String userName, String password){ // method to authenticate username and password
        boolean isValidUserName = userName.equalsIgnoreCase("Amritpal Singh");
        boolean isValidPassword = password.equalsIgnoreCase("Khehra");

        return isValidUserName&& isValidPassword; // returns true if both are same , else false
    }
}
