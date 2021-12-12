package com.example.trackon.controller;

import com.example.trackon.entity.marker.Marker;
import com.example.trackon.entity.marker.MarkerRepository;
import com.example.trackon.error.exceptions.MarkerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marker")
@RequiredArgsConstructor
public class MarkerController {

    private final MarkerRepository markerRepository;

    @PutMapping("/{markerId}")
    public void updateLocation(@PathVariable Long markerId,
                               @RequestParam Double longitude,
                               @RequestParam Double latitude) {

        Marker marker = markerRepository.findByMarkerId(markerId)
                .orElseThrow(MarkerNotFoundException::new);

        markerRepository.save(marker.updateLocation(longitude, latitude));
    }
}
