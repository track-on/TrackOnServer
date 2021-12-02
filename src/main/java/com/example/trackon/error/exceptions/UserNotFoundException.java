package com.example.trackon.error.exceptions;

import com.example.trackon.error.exception.ErrorCode;
import com.example.trackon.error.exception.TrackOnException;

public class UserNotFoundException extends TrackOnException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
