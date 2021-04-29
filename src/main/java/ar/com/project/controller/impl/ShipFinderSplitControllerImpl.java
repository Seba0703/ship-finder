package ar.com.project.controller.impl;

import ar.com.project.controller.ShipFinderSplitController;
import ar.com.project.controller.request.model.Message;
import ar.com.project.controller.response.ACK;
import ar.com.project.controller.response.DecodedMessage;
import ar.com.project.exception.BaseException;
import ar.com.project.service.SatelliteService;
import ar.com.project.service.ShipSplitFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static ar.com.project.exception.ExceptionBuilder.build;

@RestController
@RequestMapping("/topsecret_split")
public class ShipFinderSplitControllerImpl implements ShipFinderSplitController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipFinderSplitControllerImpl.class);

    @Autowired
    private SatelliteService satelliteServ;

    @Autowired
    private ShipSplitFacade shipSplitFacade;

    @PostMapping(value = "/{satellite_name}", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ACK topSecretSplit(@RequestBody Message messages, @PathVariable(name = "satellite_name") String satelliteName) {

        try {

            satelliteServ.saveMessage(satelliteName, messages.getWords(), messages.getDistance());
            return new ACK();

        } catch (BaseException e) {
            throw build(LOGGER, e);
        } catch (Exception e) {
            throw build(LOGGER, e);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public DecodedMessage getMessage() {

        try {
            return shipSplitFacade.getMessage();
        } catch (BaseException e) {
            throw build(LOGGER, e);
        } catch (Exception e) {
            throw build(LOGGER, e);
        }

    }
}
