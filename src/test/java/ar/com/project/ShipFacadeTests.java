package ar.com.project;

import ar.com.project.builder.MessageBuilder;
import ar.com.project.builder.MessageDTOBuilder;
import ar.com.project.controller.request.Messages;
import ar.com.project.controller.response.DecodedMessage;
import ar.com.project.controller.response.model.Position;
import ar.com.project.exception.BaseException;
import ar.com.project.service.MessageDecoderService;
import ar.com.project.service.ShipFinderFacade;
import ar.com.project.service.impl.ShipFacadeImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ShipFacadeTests {

    @InjectMocks
    private ShipFacadeImpl shipFacade;

    @Mock
    private ShipFinderFacade shipFinderFacade;

    @Mock
    private MessageDecoderService mssgDecoderServ;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void GIVEN_Mensaje_WHEN_SeBuscaDecodificarlo_THEN_MensajeDecodificado() throws BaseException {
        Messages msg = MessageBuilder.build();
        Position pos = new Position(5.0, 5.0);

        when(shipFinderFacade.findShip(any())).thenReturn(pos);

        when(mssgDecoderServ.getMessage(any())).thenReturn(MessageDTOBuilder.build());

        DecodedMessage decodedMessage = shipFacade.findAndDecode(msg);

        assertNotNull(decodedMessage);
        assertNotNull(decodedMessage.getPosition());
        assertTrue(StringUtils.isNotEmpty(decodedMessage.getMessage()));
    }

    @Test
    public void GIVEN_Mensaje_WHEN_SeBuscaDecodificarlo_THEN_ExceptionEsLanzada() {
        Messages msg = MessageBuilder.build();
        Position pos = new Position(5.0, 5.0);

        try {
            when(shipFinderFacade.findShip(any())).thenThrow(new BaseException());
            when(mssgDecoderServ.getMessage(any())).thenReturn(MessageDTOBuilder.build());

            shipFacade.findAndDecode(msg);
            fail();
        } catch (BaseException e) {
            assertTrue(true);
        }

    }


}
