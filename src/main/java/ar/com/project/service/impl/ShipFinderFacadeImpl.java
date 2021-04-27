package ar.com.project.service.impl;

import ar.com.project.controller.request.model.SatelliteMessage;
import ar.com.project.controller.response.model.Position;
import ar.com.project.dto.SatellitesPositionDTO;
import ar.com.project.exception.BaseException;
import ar.com.project.service.LocationService;
import ar.com.project.service.SatelliteService;
import ar.com.project.service.ShipFinderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipFinderFacadeImpl implements ShipFinderFacade {

    @Autowired
    private LocationService locationServ;

    @Autowired
    private SatelliteService satelliteServ;

    public Position findShip(List<SatelliteMessage> satelliteMessages) throws BaseException {

        SatellitesPositionDTO satellitePositions = satelliteServ.getSatellitePositions();
        double[] distances = satelliteServ.getDistances(satelliteMessages);

        double[] position = locationServ.getLocation(satellitePositions.getMatrix(), distances);

        return new Position().build(position);
    }



}
