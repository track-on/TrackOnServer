package com.example.trackon.controller;

import com.example.trackon.payload.request.SignUpRequest;
import com.example.trackon.payload.response.UserResponse;
import com.example.trackon.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserResponse getUserInfo(@RequestHeader("Authorization") String token) {
        return userService.getUserInfo(token);
    }

    @GetMapping("/info/{userId}")
    public UserResponse getTargetInfo(@RequestHeader("Authorization") String token,
                                      @PathVariable Long userId) {
        return userService.getTargetInfo(token, userId);
    }

    @PostMapping
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
    }

    @PutMapping("/{userId}")
    public void makeAdmin(@RequestHeader("Authorization") String token,
                          @PathVariable Long userId) {
        userService.makeAdmin(token, userId);
    }
}
