package ar.com.project.builder;

import ar.com.project.controller.request.Messages;
import ar.com.project.controller.request.model.SatelliteMessage;

import java.util.List;

public class MessageBuilder {

    public static Messages build() {
        Messages msg = new Messages();

        List<SatelliteMessage> satelliteMessages = SatelliteMessageBuilder.buildLists();
        msg.setSatellites(satelliteMessages);
        return msg;
    }
}
