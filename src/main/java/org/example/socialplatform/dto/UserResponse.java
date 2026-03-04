package org.example.socialplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.processing.Generated;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Long Id;
    private String username;
    private String email;
}
