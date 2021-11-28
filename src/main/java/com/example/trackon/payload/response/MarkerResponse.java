package com.example.trackon.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MarkerResponse {
    private Long markerId;
    private String name;
    private Double longitude;
    private Double latitude;
}
