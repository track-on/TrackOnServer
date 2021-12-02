package com.example.trackon.error.exception;

import lombok.Getter;

@Getter
public class TrackOnException extends RuntimeException {

    private final ErrorCode errorCode;

    public TrackOnException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public TrackOnException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
