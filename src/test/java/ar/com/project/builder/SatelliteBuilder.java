package ar.com.project.builder;

import ar.com.project.entity.Satellite;
import ar.com.project.entity.SatellitePosition;

public class SatelliteBuilder {

    public static final String SATO = "Sato";
    public static final String SKYWALKER = "Skywalker";
    public static final String KENOBI = "Kenobi";

    public static Satellite buildSatoSatellite() {
        SatellitePosition pos = new SatellitePosition();
        pos.setX(10.1);
        pos.setY(10.1);
        return new Satellite(1, SATO, pos, null);
    }

    public static Satellite buildSatoSatelliteWithInfo() {
        Satellite pos = buildSatoSatellite();
        pos.setSatelliteInfo(SatelliteInfoBuilder.build());
        return pos;
    }

    public static Satellite buildSkywalkerSatellite() {
        SatellitePosition pos = new SatellitePosition();
        pos.setX(1.1);
        pos.setY(1.1);
        return new Satellite(1, SKYWALKER, pos, null);
    }

    public static Satellite buildSkywalkerSatelliteWithInfo() {
        Satellite pos = buildSkywalkerSatellite();
        pos.setSatelliteInfo(SatelliteInfoBuilder.build());
        return pos;
    }

    public static Satellite buildKenobiSatellite() {
        SatellitePosition pos = new SatellitePosition();
        pos.setX(1000.1);
        pos.setY(100.1);
        return new Satellite(1, KENOBI, pos, null);
    }

    public static Satellite buildKenobiSatelliteWithInfo() {
        Satellite pos = buildKenobiSatellite();
        pos.setSatelliteInfo(SatelliteInfoBuilder.build());
        return pos;
    }
}
