package ar.com.project.builder;

import ar.com.project.entity.SatelliteInfo;

public class SatelliteInfoBuilder {
    public static SatelliteInfo build() {
        SatelliteInfo info = new SatelliteInfo();
        info.setDistance(10);
        info.setMessage("ANTERIOR,3,3,2,");
        info.setSatelliteMessageID(1);
        return info;
    }
}
