package com.example.trackon.entity.marker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, Long> {
    List<Marker> findAll();
    Optional<Marker> findByMarkerId(Long markerId);
}
