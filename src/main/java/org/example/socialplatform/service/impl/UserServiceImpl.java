package org.example.socialplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.socialplatform.dto.LoginRequest;
import org.example.socialplatform.dto.LoginResponse;
import org.example.socialplatform.dto.UserRegisterRequest;
import org.example.socialplatform.dto.UserResponse;
import org.example.socialplatform.entity.User;
import org.example.socialplatform.repository.PostRepository;
import org.example.socialplatform.repository.UserRepository;
import org.example.socialplatform.security.JwtService;
import org.example.socialplatform.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;



    @Override
    public UserResponse register(UserRegisterRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("username already taken");
        }
        if(userRepository.existsByEmail(request.getUsername())){
            throw new RuntimeException("Email already register");
        }
        User user= new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved=userRepository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail()
        );




    }
    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getId());
        return new LoginResponse(token);
    }
}
