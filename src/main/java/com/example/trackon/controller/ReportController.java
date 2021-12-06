package com.example.trackon.controller;

import com.example.trackon.payload.response.ReportResponse;
import com.example.trackon.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/me")
    public List<ReportResponse> getMyReport(@RequestHeader("Authorization") String token) {
        return reportService.getMyReport(token);
    }

    @GetMapping
    public List<ReportResponse> getReport(@RequestHeader("Authorization") String token) {
        return reportService.getAllReport(token);
    }

    @PostMapping("/{markerId}")
    public void report(@RequestHeader("Authorization") String token,
                       @RequestParam String message,
                       @PathVariable Long markerId) {
        reportService.report(token, markerId, message);
    }

    @PutMapping("/submit/{reportId}")
    public void submitReport(@RequestHeader("Authorization") String token,
                             @PathVariable Long reportId) {
        reportService.submitReport(token, reportId);
    }

    @DeleteMapping("/{reportId}")
    public void deleteReport(@RequestHeader("Authorization") String token,
                             @PathVariable Long reportId) {
        reportService.deleteReport(token, reportId );
    }
}
