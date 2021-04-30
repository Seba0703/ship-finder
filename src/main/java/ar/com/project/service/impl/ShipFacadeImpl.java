package ar.com.project.service.impl;

import ar.com.project.controller.request.Messages;
import ar.com.project.controller.request.model.SatelliteMessage;
import ar.com.project.controller.response.DecodedMessage;
import ar.com.project.controller.response.model.Position;
import ar.com.project.dto.MessageDTO;
import ar.com.project.exception.BaseException;
import ar.com.project.exception.NoSufficientDataException;
import ar.com.project.service.MessageDecoderService;
import ar.com.project.service.ShipFacade;
import ar.com.project.service.ShipFinderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipFacadeImpl implements ShipFacade {

    @Autowired
    private ShipFinderFacade shipFinderFacade;

    @Autowired
    private MessageDecoderService mssgDecoderServ;


    @Override
    public DecodedMessage findAndDecode(Messages messages) throws BaseException {

        Position position = shipFinderFacade.findShip(messages.getSatellites());

        List<List<String>> mssgs = this.buildMessages(messages.getSatellites());
        MessageDTO mssg = mssgDecoderServ.getMessage(mssgs);

        return new DecodedMessage(position, mssg.getMessage());
    }

    private List<List<String>> buildMessages(List<SatelliteMessage> satellites) throws BaseException {
        List<List<String>> mssgs = new ArrayList<>();
        for(SatelliteMessage satelliteMessage: satellites) {
            if(CollectionUtils.isEmpty(satelliteMessage.getMessage())) {
                throw new NoSufficientDataException();
            }
            mssgs.add(satelliteMessage.getMessage());
        }

        return mssgs;
    }


}
