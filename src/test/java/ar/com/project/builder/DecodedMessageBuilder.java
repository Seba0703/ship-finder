package ar.com.project.builder;

import ar.com.project.controller.response.DecodedMessage;
import ar.com.project.controller.response.model.Position;

public class DecodedMessageBuilder {

    public static DecodedMessage build() {
        DecodedMessage decodedMessage = new DecodedMessage();

        decodedMessage.setMessage("ASD ASD sdddd sdad");
        decodedMessage.setPosition(new Position(5.0, 56.8));

        return decodedMessage;
    }
}
