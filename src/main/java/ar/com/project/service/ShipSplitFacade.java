package ar.com.project.service;

import ar.com.project.controller.response.DecodedMessage;
import ar.com.project.exception.BaseException;

public interface ShipSplitFacade {
    DecodedMessage getMessage() throws BaseException;
}
