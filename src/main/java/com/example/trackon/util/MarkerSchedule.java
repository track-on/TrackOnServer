package com.example.trackon.util;

import com.corundumstudio.socketio.SocketIOServer;
import com.example.trackon.entity.marker.Marker;
import com.example.trackon.entity.marker.MarkerRepository;
import com.example.trackon.payload.response.MarkerResponse;
import com.example.trackon.payload.response.MarkerResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MarkerSchedule {

    private final MarkerRepository markerRepository;
    private final SocketIOServer server;

    @Scheduled(cron = "*/10 * * * * *")
    public void maker() {
        log.info("marker");

        List<Marker> markers = markerRepository.findAll();
        List<MarkerResponse> markerResponses = new ArrayList<>();
        for (Marker marker : markers) {
            markerResponses.add(
                    MarkerResponse.builder()
                            .markerId(marker.getMarkerId())
                            .name(marker.getName())
                            .latitude(marker.getLatitude())
                            .longitude(marker.getLongitude())
                            .build()
            );
        }

        server.getRoomOperations("marker").sendEvent("marker",
                MarkerResponseList.builder()
                        .markerResponses(markerResponses)
                        .build()
                );
    }
}
