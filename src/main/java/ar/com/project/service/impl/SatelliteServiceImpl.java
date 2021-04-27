package ar.com.project.service.impl;

import ar.com.project.controller.request.model.SatelliteMessage;
import ar.com.project.dao.SatelliteRepository;
import ar.com.project.dto.SatellitesPositionDTO;
import ar.com.project.entity.Satellite;
import ar.com.project.entity.SatelliteInfo;
import ar.com.project.exception.BaseException;
import ar.com.project.exception.ObjectNotFoundException;
import ar.com.project.service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
            throw new BaseException();
        }
    }

    public void saveMessage(String satelliteName, List<String> messages, double distance ) throws ObjectNotFoundException {

        Optional<Satellite> op = this.findSatellite(satelliteName);

        if(op.isPresent()) {
            Satellite sat = op.get();

            this.buildInfo(sat, messages, distance);

            satelliteRepo.save(sat);
        } else {
            throw new ObjectNotFoundException();
        }
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
