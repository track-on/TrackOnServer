package com.example.trackon.service.auth;

import com.example.trackon.payload.request.SignInRequest;
import com.example.trackon.payload.response.TokenResponse;

public interface AuthService {
    TokenResponse signIn(SignInRequest signInRequest);
    TokenResponse signInAdmin(SignInRequest signInRequest);
    TokenResponse refreshToken(String refreshToken);
}
