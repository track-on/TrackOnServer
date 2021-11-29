package com.example.trackon.payload.request;

import lombok.Getter;

@Getter
public class SignUpRequest {
    private String id;
    private String password;
    private String nickName;
    private Integer age;
    private String phoneNumber;
}
