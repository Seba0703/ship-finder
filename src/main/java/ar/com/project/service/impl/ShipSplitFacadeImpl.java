package ar.com.project.service.impl;

import ar.com.project.controller.request.Messages;
import ar.com.project.controller.response.DecodedMessage;
import ar.com.project.exception.BaseException;
import ar.com.project.service.SatelliteService;
import ar.com.project.service.ShipFacade;
import ar.com.project.service.ShipSplitFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
