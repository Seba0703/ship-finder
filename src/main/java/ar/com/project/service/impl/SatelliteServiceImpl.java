package ar.com.project.service.impl;

import ar.com.project.controller.request.Messages;
import ar.com.project.controller.request.model.SatelliteMessage;
import ar.com.project.dao.SatelliteRepository;
import ar.com.project.dto.SatellitesPositionDTO;
import ar.com.project.entity.Satellite;
import ar.com.project.entity.SatelliteInfo;
import ar.com.project.exception.*;
import ar.com.project.service.SatelliteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class SatelliteServiceImpl implements SatelliteService {

    public static final Integer CANTIDAD_SATELITES = 3;

    @Autowired
    private SatelliteRepository satelliteRepo;

    public SatellitesPositionDTO getSatellitePositions() throws BaseException {

        List<Satellite> satellites = satelliteRepo.findAll();

        if(!CollectionUtils.isEmpty(satellites)
                && this.hasAllSatellites(satellites)) {

            double[][] positions = new double[satellites.size()][2];
            for (int i = 0; i < satellites.size(); i++) {
                Satellite satellite = satellites.get(i);
                positions[i][0] = satellite.getSatellitePosition().getX();
                positions[i][1] = satellite.getSatellitePosition().getY();
            }

            return new SatellitesPositionDTO(positions);
        } else {
            throw new SatelliteEmptyException();
        }
    }

    public void saveMessage(String satelliteName, List<String> messages, double distance ) throws BaseException {

        this.validate(satelliteName, messages, distance);

        Optional<Satellite> op = this.findSatellite(satelliteName);

        if(op.isPresent()) {
            Satellite sat = op.get();

            this.buildInfo(sat, messages, distance);

            satelliteRepo.save(sat);
        } else {
            throw new ObjectNotFoundException();
        }
    }

    private void validate(String satelliteName, List<String> messages, double distance) throws BaseException {
        if(StringUtils.isEmpty(satelliteName)) {
            throw new SatelliteEmptyException();
        }

        if(CollectionUtils.isEmpty(messages)) {
            throw new EmptyMessageException();
        }

        if(distance < 0) {
            throw new DistanceException();
        }
    }

    public Messages getMessages() throws BaseException {
        List<Satellite> satellites = satelliteRepo.findAll();

        if(this.validate(satellites)) {

            return this.buildMessages(satellites);
        }

        throw new NoSufficientDataException();
    }

    private boolean validate(List<Satellite> satellites) {
        return this.checkSatsSize(satellites)
                && this.checkInfo(satellites)
                && this.checkPosition(satellites);
    }

    private boolean checkSatsSize(List<Satellite> satellites) {
        return !CollectionUtils.isEmpty(satellites) && satellites.size() == CANTIDAD_SATELITES;
    }

    private boolean checkInfo(List<Satellite> satellites) {
        return satellites.stream().filter(sat -> Objects.nonNull(sat.getSatelliteInfo())).count() == CANTIDAD_SATELITES;
    }

    private boolean checkPosition(List<Satellite> satellites) {
        return satellites.stream().filter(sat -> Objects.nonNull(sat.getSatellitePosition())).count() == CANTIDAD_SATELITES;
    }

    private Messages buildMessages(List<Satellite> satellites) {
        Messages mssg = new Messages();
        List<SatelliteMessage> satMssgs = new ArrayList<>();
        mssg.setSatellites(satMssgs);
        for(Satellite sat: satellites) {
            SatelliteMessage satMssg = new SatelliteMessage();
            satMssg.setName(sat.getName());
            satMssg.setWords(this.transformToList(sat.getSatelliteInfo().getMessage()));
            satMssg.setDistance(sat.getSatelliteInfo().getDistance());
            satMssgs.add(satMssg);
        }

        return mssg;
    }

    private List<String> transformToList(String message) {
        return Arrays.asList(message.split(","));
    }

    private void buildInfo(Satellite sat, List<String> messages, double distance) {
        SatelliteInfo satMssg = this.getInfo(sat);

        satMssg.setMessage(String.join(",", messages));
        satMssg.setDistance(distance);
        sat.setSatelliteInfo(satMssg);
    }

    private SatelliteInfo getInfo(Satellite sat) {
        if(Objects.isNull(sat.getSatelliteInfo())) {
            return new SatelliteInfo();
        } else {
            return sat.getSatelliteInfo();
        }
    }

    private Optional<Satellite> findSatellite(String satelliteName) {
        Satellite sat = new Satellite();
        sat.setName(satelliteName);
        Example<Satellite> example = Example.of(sat);

        return satelliteRepo.findOne(example);
    }



    public boolean hasAllSatellites(List<Satellite> satellites) {
        return satellites.size() == CANTIDAD_SATELITES;
    }

    public double[] getDistances(List<SatelliteMessage> satelliteMessages) {

        return satelliteMessages.stream().mapToDouble(SatelliteMessage::getDistance).toArray();

    }
}
