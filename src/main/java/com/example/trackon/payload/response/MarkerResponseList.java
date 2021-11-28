package com.example.trackon.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MarkerResponseList {
    private List<MarkerResponse> markerResponses;
}
