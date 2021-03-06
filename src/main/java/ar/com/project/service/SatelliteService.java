package ar.com.project.service;

import ar.com.project.controller.request.Messages;
import ar.com.project.controller.request.model.SatelliteMessage;
import ar.com.project.dto.SatellitesPositionDTO;
import ar.com.project.exception.BaseException;

import java.util.List;

public interface SatelliteService {
    SatellitesPositionDTO getSatellitePositions() throws BaseException;
    double[] getDistances(List<SatelliteMessage> satelliteMessages);
    void saveMessage(String satelliteName, List<String> messages, double distance ) throws BaseException;
    Messages getMessages() throws BaseException;
}
