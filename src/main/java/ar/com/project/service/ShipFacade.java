package ar.com.project.service;

import ar.com.project.controller.request.Messages;
import ar.com.project.controller.response.DecodedMessage;
import ar.com.project.exception.BaseException;

public interface ShipFacade {

    DecodedMessage findAndDecode(Messages messages) throws BaseException;
}
