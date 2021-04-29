package ar.com.project.builder;

import ar.com.project.controller.request.model.SatelliteMessage;

import java.util.ArrayList;
import java.util.List;

import static ar.com.project.builder.SatelliteBuilder.SATO;

public class SatelliteMessageBuilder {

    public static SatelliteMessage build() {
        SatelliteMessage sat = new SatelliteMessage();
        sat.setDistance(5.0);
        List<String> words = new ArrayList<>();
        words.add("asd");
        words.add("dd");
        sat.setWords(words);
        sat.setName(SATO);
        return sat;
    }

    public static List<SatelliteMessage> buildLists() {
        List<SatelliteMessage> sats = new ArrayList<>();
        sats.add(build());
        sats.add(build());
        sats.add(build());

        return sats;
    }
}
