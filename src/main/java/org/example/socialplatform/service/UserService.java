package org.example.socialplatform.service;

import org.example.socialplatform.dto.LoginRequest;
import org.example.socialplatform.dto.LoginResponse;
import org.example.socialplatform.dto.UserRegisterRequest;
import org.example.socialplatform.dto.UserResponse;

public interface UserService {
    UserResponse register(UserRegisterRequest request);
    LoginResponse login(LoginRequest request);

}
