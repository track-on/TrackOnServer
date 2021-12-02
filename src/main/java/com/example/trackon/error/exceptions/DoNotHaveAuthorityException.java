package com.example.trackon.error.exceptions;

import com.example.trackon.error.exception.ErrorCode;
import com.example.trackon.error.exception.TrackOnException;

public class DoNotHaveAuthorityException extends TrackOnException {
    public DoNotHaveAuthorityException() {
        super(ErrorCode.DO_NOT_HAVE_AUTHORITY);
    }
}
