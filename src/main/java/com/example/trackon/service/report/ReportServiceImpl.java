package com.example.trackon.service.report;

import com.example.trackon.entity.marker.Marker;
import com.example.trackon.entity.marker.MarkerRepository;
import com.example.trackon.entity.report.Report;
import com.example.trackon.entity.report.ReportRepository;
import com.example.trackon.entity.report.ReportType;
import com.example.trackon.entity.user.Authority;
import com.example.trackon.entity.user.User;
import com.example.trackon.entity.user.UserRepository;
import com.example.trackon.error.exceptions.DoNotHaveAuthorityException;
import com.example.trackon.error.exceptions.MarkerNotFoundException;
import com.example.trackon.error.exceptions.ReportNotFoundException;
import com.example.trackon.error.exceptions.UserNotFoundException;
import com.example.trackon.jwt.JwtProvider;
import com.example.trackon.payload.response.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final MarkerRepository markerRepository;

    private final JwtProvider jwtProvider;

    @Override
    public void report(String token, Long userId, String message) {
        User user = userRepository.findByUserId(jwtProvider.getUserId(token))
                .orElseThrow(UserNotFoundException::new);

        Marker marker = markerRepository.findByMarkerId(userId)
                .orElseThrow(MarkerNotFoundException::new);

        reportRepository.save(
                Report.builder()
                        .reporter(user)
                        .marker(marker)
                        .message(message)
                        .reportType(ReportType.PROGRESS)
                        .reportAt(LocalDateTime.now())
                        .reporter(user)
                        .build()
        );
    }

    @Override
    public void submitReport(String token, Long reportId) {
        User user = userRepository.findByUserId(jwtProvider.getUserId(token))
                .orElseThrow(UserNotFoundException::new);

        if(!user.getAuthority().equals(Authority.ADMIN))
            throw new DoNotHaveAuthorityException();

        Report report = reportRepository.findByReportId(reportId)
                .orElseThrow(ReportNotFoundException::new);

        reportRepository.save(report.updateReportType(ReportType.INCREMENT));
    }

    @Override
    @Transactional
    public void deleteReport(String token, Long reportId) {
        User user = userRepository.findByUserId(jwtProvider.getUserId(token))
                .orElseThrow(UserNotFoundException::new);

        if(!user.getAuthority().equals(Authority.ADMIN))
            throw new DoNotHaveAuthorityException();

        reportRepository.findByReportId(reportId)
                        .orElseThrow(ReportNotFoundException::new);

        reportRepository.deleteByReportId(reportId);
    }

    @Override
    public List<ReportResponse> getMyReport(String token) {
        User user = userRepository.findByUserId(jwtProvider.getUserId(token))
                .orElseThrow(UserNotFoundException::new);

        List<Report> reports = reportRepository.findAllByReporterOrderByReportAtDesc(user);
        return getReportResponses(reports);
    }

    @Override
    public List<ReportResponse> getAllReport(String token) {
        User user = userRepository.findByUserId(jwtProvider.getUserId(token))
                .orElseThrow(UserNotFoundException::new);

        if(!user.getAuthority().equals(Authority.ADMIN))
            throw new DoNotHaveAuthorityException();

        List<Report> reports = reportRepository.findAll(Sort.by(Sort.Direction.DESC, "reportAt"));

        return getReportResponses(reports);
    }

    private List<ReportResponse> getReportResponses(List<Report> reports) {
        List<ReportResponse> reportResponses = new ArrayList<>();

        for (Report report : reports) {
            reportResponses.add(
                    ReportResponse.builder()
                            .reportId(report.getReportId())
                            .reporterId(report.getReporter().getUserId())
                            .markerId(report.getMarker().getMarkerId())
                            .latitude(report.getMarker().getLatitude())
                            .longitude(report.getMarker().getLongitude())
                            .name(report.getMarker().getName())
                            .reporter(report.getReporter().getName())
                            .message(report.getMessage())
                            .reportAt(report.getReportAt().toLocalDate().toString())
                            .reportType(report.getReportType())
                            .build()
            );
        }

        return reportResponses;
    }
}
