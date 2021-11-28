package com.example.trackon.service.user;

import com.example.trackon.payload.request.SignUpRequest;
import com.example.trackon.payload.response.UserResponse;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);
    UserResponse getUserInfo(String token);
}
