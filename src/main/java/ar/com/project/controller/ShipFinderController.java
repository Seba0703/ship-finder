package ar.com.project.controller;

import ar.com.project.controller.request.Messages;
import ar.com.project.controller.response.DecodedMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ShipFinderController {

    DecodedMessage topSecret(Messages messages);
}
