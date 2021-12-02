package com.example.trackon.error.exceptions;

import com.example.trackon.error.exception.ErrorCode;
import com.example.trackon.error.exception.TrackOnException;

public class MarkerNotFoundException extends TrackOnException {
    public MarkerNotFoundException() {
        super(ErrorCode.MARKER_NOT_FOUND);
    }
}
