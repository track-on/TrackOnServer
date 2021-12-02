package com.example.trackon.error.exceptions;

import com.example.trackon.error.exception.ErrorCode;
import com.example.trackon.error.exception.TrackOnException;

public class LoginFailedException extends TrackOnException {
    public LoginFailedException() {
        super(ErrorCode.LOGIN_FAILED);
    }
}
