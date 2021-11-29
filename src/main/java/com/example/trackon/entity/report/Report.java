package com.example.trackon.entity.report;

import com.example.trackon.entity.marker.Marker;
import com.example.trackon.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;

    private String message;

    private ReportType reportType;

    private LocalDateTime reportAt;

    @ManyToOne
    @JoinColumn(name = "user")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "marker")
    private Marker marker;

    public Report updateReportType(ReportType reportType) {
        this.reportType = reportType;

        return this;
    }
}
