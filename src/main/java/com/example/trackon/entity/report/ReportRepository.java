package com.example.trackon.entity.report;

import com.example.trackon.entity.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByReporterOrderByReportAtDesc(User reporter);
    List<Report> findAll(Sort sort);
    Optional<Report> findByReportId(Long reportId);
    void deleteByReportId(Long reportId);
}
