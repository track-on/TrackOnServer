package com.example.trackon.payload.response;

import com.example.trackon.entity.report.ReportType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportResponse {
    private Long reportId;
    private Long markerId;
    private Long reporterId;
    private String reporter;
    private String name;
    private String message;
    private String reportAt;
    private ReportType reportType;
    private Double longitude;
    private Double latitude;
}

