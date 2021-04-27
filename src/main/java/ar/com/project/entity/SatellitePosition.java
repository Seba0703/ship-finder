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
@Entity(name = "Satellite_position")
public class SatellitePosition {

    @Id
    @Column(name = "SATELLITE_POSITION_ID", updatable = false)
    private Integer satellitePositionID;

    @OneToOne (mappedBy="satellitePosition")
    private Satellite satellite;

    @Column(name = "X", nullable = false)
    private Double x;

    @Column(name = "Y", nullable = false)
    private Double y;
}
