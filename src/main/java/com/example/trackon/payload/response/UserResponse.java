package com.example.trackon.payload.response;

import com.example.trackon.entity.user.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private Long userId;
    private String nickName;
    private String phoneNumber;
    private Integer age;
    private Authority authority;
}
