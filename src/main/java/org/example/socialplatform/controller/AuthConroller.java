package org.example.socialplatform.controller;

import org.example.socialplatform.dto.LoginRequest;
import org.example.socialplatform.dto.LoginResponse;
import org.example.socialplatform.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthConroller {
    private final UserService userService;
    public AuthConroller(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/login")
    public LoginResponse login (@RequestBody LoginRequest request){
        return userService.login(request);
    }

}
