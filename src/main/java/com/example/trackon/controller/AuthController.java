package com.example.trackon.controller;

import com.example.trackon.payload.request.SignInRequest;
import com.example.trackon.payload.response.TokenResponse;
import com.example.trackon.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public TokenResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @PostMapping("/admin")
    public TokenResponse signInAdmin(@RequestBody SignInRequest signInRequest) {
        return authService.signInAdmin(signInRequest);
    }

    @PutMapping
    public TokenResponse refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}
