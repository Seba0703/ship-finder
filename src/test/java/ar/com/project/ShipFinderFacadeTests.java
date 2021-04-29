package ar.com.project;

import ar.com.project.builder.SatelliteMessageBuilder;
import ar.com.project.builder.SatellitesPositionDTOBuilder;
import ar.com.project.controller.response.model.Position;
import ar.com.project.exception.BaseException;
import ar.com.project.exception.SatelliteEmptyException;
import ar.com.project.exception.ShipNotFoundException;
import ar.com.project.service.LocationService;
import ar.com.project.service.SatelliteService;
import ar.com.project.service.impl.ShipFinderFacadeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ShipFinderFacadeTests {

    @InjectMocks
    private ShipFinderFacadeImpl shipFinderFacade;

    @Mock
    private LocationService locationServ;

    @Mock
    private SatelliteService satelliteServ;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void GIVEN_S() throws BaseException {

        when(satelliteServ.getSatellitePositions()).thenReturn(SatellitesPositionDTOBuilder.build());
        when(satelliteServ.getDistances(any())).thenReturn(new double[]{1.0, 1.0, 1.0});

        when(locationServ.getLocation(any(), any())).thenReturn(new double[]{1.1, 1.1});

        Position pos = shipFinderFacade.findShip(SatelliteMessageBuilder.buildLists());

        assertNotNull(pos);
        assertNotNull(pos.getX());
        assertNotNull(pos.getY());
    }

    @Test
    public void GIVEN_UnaBusqueda_WHEN_NoHaySatelitesCerca_THEN_ExceptionEsLanzada() throws BaseException {

        when(satelliteServ.getSatellitePositions()).thenThrow(new SatelliteEmptyException());
        try {
            shipFinderFacade.findShip(SatelliteMessageBuilder.buildLists());
            fail();
        } catch (SatelliteEmptyException e) {
            assertTrue(true);
        }
    }

    @Test
    public void GIVEN_UnaBusqueda_WHEN_LaNaveNoEsEncontrada_THEN_ExceptionEsLanzada() throws BaseException {

        when(satelliteServ.getSatellitePositions()).thenReturn(SatellitesPositionDTOBuilder.build());
        when(locationServ.getLocation(any(), any())).thenThrow(new ShipNotFoundException());
        try {
            shipFinderFacade.findShip(SatelliteMessageBuilder.buildLists());
            fail();
        } catch (ShipNotFoundException e) {
            assertTrue(true);
        }
    }
}
