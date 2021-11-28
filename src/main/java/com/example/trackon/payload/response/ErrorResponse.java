package com.example.trackon.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private Integer status;
    private String message;
}
