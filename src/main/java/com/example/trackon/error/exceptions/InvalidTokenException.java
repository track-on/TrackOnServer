package com.example.trackon.error.exceptions;

import com.example.trackon.error.exception.ErrorCode;
import com.example.trackon.error.exception.TrackOnException;

public class InvalidTokenException extends TrackOnException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
