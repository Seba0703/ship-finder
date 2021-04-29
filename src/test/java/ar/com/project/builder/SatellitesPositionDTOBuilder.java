package ar.com.project.builder;

import ar.com.project.dto.SatellitesPositionDTO;

public class SatellitesPositionDTOBuilder {

    public static SatellitesPositionDTO build() {
        SatellitesPositionDTO pos = new SatellitesPositionDTO();
        double[][] mat = new double[3][2];
        mat[0][0] = 1.0;
        mat[0][1] = 1.0;
        mat[1][0] = 1.0;
        mat[1][1] = 1.0;
        mat[2][0] = 1.0;
        mat[2][1] = 1.0;

        pos.setMatrix(mat);

        return pos;
    }
}
