package ar.com.project.service.impl;

import ar.com.project.controller.request.Messages;
import ar.com.project.controller.request.model.SatelliteMessage;
import ar.com.project.controller.response.DecodedMessage;
import ar.com.project.dao.SatelliteRepository;
import ar.com.project.entity.Satellite;
import ar.com.project.exception.BaseException;
import ar.com.project.exception.NoSufficientDataException;
import ar.com.project.service.SatelliteService;
import ar.com.project.service.ShipFacade;
import ar.com.project.service.ShipSplitFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ar.com.project.service.impl.SatelliteServiceImpl.CANTIDAD_SATELITES;

@Service
public class ShipSplitFacadeImpl implements ShipSplitFacade {

    @Autowired
    private SatelliteService satelliteServ;

    @Autowired
    private ShipFacade shipFacade;


    @Override
    public DecodedMessage getMessage() throws BaseException {

        Messages mssg = satelliteServ.getMessages();

        return shipFacade.findAndDecode(mssg);

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
}
