package ar.com.project.builder;

import ar.com.project.entity.Satellite;

import java.util.ArrayList;
import java.util.List;

public class SatellitesBuilder {

    public static List<Satellite> buildSatellites() {
        List<Satellite> satellites = new ArrayList<>();
        Satellite sato = SatelliteBuilder.buildSatoSatellite();
        Satellite skywalker = SatelliteBuilder.buildSkywalkerSatellite();
        Satellite kenobi = SatelliteBuilder.buildKenobiSatellite();
        satellites.add(sato);
        satellites.add(kenobi);
        satellites.add(skywalker);
        return satellites;
    }

    public static List<Satellite> buildTwoSatellites() {
        List<Satellite> satellites = new ArrayList<>();
        Satellite sato = SatelliteBuilder.buildSatoSatellite();
        Satellite skywalker = SatelliteBuilder.buildSkywalkerSatellite();
        satellites.add(sato);
        satellites.add(skywalker);
        return satellites;
    }

    public static double[][] buildMatrix() {
        Satellite sato = SatelliteBuilder.buildSatoSatellite();
        Satellite skywalker = SatelliteBuilder.buildSkywalkerSatellite();
        Satellite kenobi = SatelliteBuilder.buildKenobiSatellite();

        double[][] matrix = new double[3][2];
        matrix[0][0] = sato.getSatellitePosition().getX();
        matrix[0][1] = sato.getSatellitePosition().getY();
        matrix[1][0] = kenobi.getSatellitePosition().getX();
        matrix[1][1] = kenobi.getSatellitePosition().getY();
        matrix[2][0] = skywalker.getSatellitePosition().getX();
        matrix[2][1] = skywalker.getSatellitePosition().getY();

        return matrix;

    }
}
