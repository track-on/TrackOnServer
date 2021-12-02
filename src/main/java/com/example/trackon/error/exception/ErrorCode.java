package com.example.trackon.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN("invalid token", 403),
    LOGIN_FAILED("login failed", 403),
    DO_NOT_HAVE_AUTHORITY("do not have authority", 409),
    ALREADY_SIGNED("already signed", 403),
    USER_NOT_FOUND("user not found", 404),
    MARKER_NOT_FOUND("marker not found", 404),
    REPORT_NOT_FOUND("report not found", 404);

    private String message;
    private int status;
}
