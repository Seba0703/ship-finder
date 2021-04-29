package ar.com.project.controller.impl;

import ar.com.project.controller.ShipFinderController;
import ar.com.project.controller.request.Messages;
import ar.com.project.controller.response.DecodedMessage;
import ar.com.project.exception.BaseException;
import ar.com.project.service.ShipFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ar.com.project.exception.ExceptionBuilder.build;

@RestController
@RequestMapping("/topsecret")
public class ShipFinderControllerImpl implements ShipFinderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipFinderControllerImpl.class);

     @Autowired
     private ShipFacade shipFacade;

    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DecodedMessage topSecret(@RequestBody Messages messages) {
        try {

            return shipFacade.findAndDecode(messages);

        } catch (BaseException e) {
            throw build(LOGGER, e);
        } catch (Exception e) {
            throw build(LOGGER, e);
        }

    }
}
