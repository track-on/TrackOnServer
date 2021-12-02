package com.example.trackon.error.exceptions;

import com.example.trackon.error.exception.ErrorCode;
import com.example.trackon.error.exception.TrackOnException;

public class AlreadySignedException extends TrackOnException {
    public AlreadySignedException() {
        super(ErrorCode.ALREADY_SIGNED);
    }
}
