package com.example.trackon.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private Long userId;
    private String nickName;
    private String phoneNumber;
    private Integer age;
}
