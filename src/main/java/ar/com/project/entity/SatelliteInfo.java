package ar.com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Satellite_info")
public class SatelliteInfo {

    @Id
    @Column(name = "SATELLITE_INFO_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer satelliteMessageID;

    @OneToOne (mappedBy="satelliteInfo")
    private Satellite satellite;

    @Column(name = "MESSAGE", nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "DISTANCE", nullable = false)
    private double distance;
}
