package ar.com.project.controller;

import ar.com.project.controller.request.Messages;
import ar.com.project.controller.request.model.Message;
import ar.com.project.controller.response.ACK;
import ar.com.project.controller.response.DecodedMessage;

public interface ShipFinderSplitController {

    ACK topSecretSplit(Message messages, String satelliteName);

    DecodedMessage getMessage();
}
