package ar.com.project.controller;

import ar.com.project.controller.request.Messages;
import ar.com.project.controller.response.DecodedMessage;

public interface ShipFinderController {

    DecodedMessage topSecret(Messages messages);
}
