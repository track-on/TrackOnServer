package com.example.trackon.service.report;

import com.example.trackon.payload.response.ReportResponse;

import java.util.List;

public interface ReportService {
    void report(String token, Long userId, String message);
    void submitReport(String token, Long reportId);
    void deleteReport(String token, Long reportId);
    List<ReportResponse> getMyReport(String token);
    List<ReportResponse> getAllReport(String token);
}
