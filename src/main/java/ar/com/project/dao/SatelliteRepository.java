package ar.com.project.dao;

import ar.com.project.entity.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SatelliteRepository extends JpaRepository<Satellite, Integer> {
}
