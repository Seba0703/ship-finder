package ar.com.project.service;

import ar.com.project.controller.request.model.SatelliteMessage;
import ar.com.project.controller.response.model.Position;
import ar.com.project.exception.BaseException;

import java.util.List;

public interface ShipFinderFacade {
    Position findShip(List<SatelliteMessage> satelliteMessages) throws BaseException;
}
