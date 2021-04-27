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
@Entity(name = "Satellite")
public class Satellite {

    @Id
    @Column(name = "SATELLITE_ID", updatable = false)
    private Integer satelliteID;

    @Basic(optional = false)
    @Column(name = "NAME", unique = true, nullable = false, columnDefinition = "TEXT")
    private String name;

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "SATELLITE_POSITION_ID", referencedColumnName = "SATELLITE_POSITION_ID")
    private SatellitePosition satellitePosition;

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "SATELLITE_INFO_ID", referencedColumnName = "SATELLITE_INFO_ID")
    private SatelliteInfo satelliteInfo;



}
