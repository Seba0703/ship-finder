package ar.com.project;

import ar.com.project.builder.DecodedMessageBuilder;
import ar.com.project.builder.MessageBuilder;
import ar.com.project.controller.response.DecodedMessage;
import ar.com.project.exception.BaseException;
import ar.com.project.exception.NoSufficientDataException;
import ar.com.project.service.SatelliteService;
import ar.com.project.service.ShipFacade;
import ar.com.project.service.impl.ShipSplitFacadeImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ShipFinderSplitFacadeTests {

    @InjectMocks
    private ShipSplitFacadeImpl shipSplitFacade;

    @Mock
    private SatelliteService satelliteServ;

    @Mock
    private ShipFacade shipFacade;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void GIVEN_SeBuscaUnMensaje_WHEN__THEN() throws BaseException {

        when(satelliteServ.getMessages()).thenReturn(MessageBuilder.build());
        when(shipFacade.findAndDecode(any())).thenReturn(DecodedMessageBuilder.build());

        DecodedMessage decodedMessage = shipSplitFacade.getMessage();

        assertNotNull(decodedMessage);
        assertNotNull(decodedMessage.getPosition());
        assertTrue(StringUtils.isNotEmpty(decodedMessage.getMessage()));

    }

    @Test
    public void GIVEN_SeBuscaUnMensaje_WHEN_NoHaySuficienteDatos_THEN_unaExceltionEsLanzada() throws BaseException {

        when(satelliteServ.getMessages()).thenThrow(new NoSufficientDataException());

        try {
            shipSplitFacade.getMessage();
            fail();
        } catch (NoSufficientDataException e) {
            assertTrue(true);
        }



    }
}
