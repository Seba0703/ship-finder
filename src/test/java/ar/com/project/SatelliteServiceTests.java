package ar.com.project;

import ar.com.project.builder.SatelliteBuilder;
import ar.com.project.builder.SatelliteInfoBuilder;
import ar.com.project.builder.SatellitesBuilder;
import ar.com.project.controller.request.Messages;
import ar.com.project.controller.request.model.SatelliteMessage;
import ar.com.project.dao.SatelliteRepository;
import ar.com.project.dto.SatellitesPositionDTO;
import ar.com.project.entity.Satellite;
import ar.com.project.exception.*;
import ar.com.project.service.impl.SatelliteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ar.com.project.builder.SatelliteBuilder.SATO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class SatelliteServiceTests {

    @InjectMocks
    private SatelliteServiceImpl satelliteServ;

    @Mock
    private SatelliteRepository satelliteRepo;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void GIVEN_TresSatelitesExistentes_WHEN_SeBuscaTodos_THEN_SatelitesSonEncontrados() throws BaseException {
        when(satelliteRepo.findAll()).thenReturn(SatellitesBuilder.buildSatellites());
        SatellitesPositionDTO positions = satelliteServ.getSatellitePositions();

        double[][] matrix = SatellitesBuilder.buildMatrix();

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                assertEquals(matrix[i][j], positions.getMatrix()[i][j]);
            }
        }
    }

    @Test
    public void GIVEN_ZeroSatelitesExistentes_WHEN_SeBuscaTodos_THEN_ExceptionEsLanzada() throws BaseException {
        when(satelliteRepo.findAll()).thenReturn(null);
        try {
            SatellitesPositionDTO positions = satelliteServ.getSatellitePositions();
            fail();
        }catch (BaseException e) {
            assertTrue(true);
        }
    }

    @Test
    public void GIVEN_TwoSatelitesExistentes_WHEN_SeBuscaTodos_THEN_ExceptionEsLanzada() throws BaseException {
        when(satelliteRepo.findAll()).thenReturn(SatellitesBuilder.buildTwoSatellites());
        try {
            SatellitesPositionDTO positions = satelliteServ.getSatellitePositions();
            fail();
        }catch (BaseException e) {
            assertTrue(true);
        }
    }

    @Test
    public void GIVEN_LlegaNuevoMensajeVacio_WHEN_SateliteValido_THEN_Exception() throws BaseException {
        Optional<Satellite> sat = Optional.of(SatelliteBuilder.buildSatoSatellite());

        Satellite sate = SatelliteBuilder.buildSatoSatellite();
        sate.setSatelliteInfo(SatelliteInfoBuilder.build());

        when(satelliteRepo.findOne(any())).thenReturn(sat);
        when(satelliteRepo.save(any())).thenReturn(null);

        try {
            satelliteServ.saveMessage("Sato", new ArrayList<>(), 4);
            fail();
        } catch (EmptyMessageException e) {
           assertTrue(true);
        }
    }

    @Test
    public void GIVEN_SateliteVacio_WHEN_SateliteValido_THEN_Exception() throws BaseException {
        Optional<Satellite> sat = Optional.of(SatelliteBuilder.buildSatoSatellite());

        Satellite sate = SatelliteBuilder.buildSatoSatellite();
        sate.setSatelliteInfo(SatelliteInfoBuilder.build());

        when(satelliteRepo.findOne(any())).thenReturn(sat);
        when(satelliteRepo.save(any())).thenReturn(null);
        List<String> msg = new ArrayList<>();
        msg.add("asdd");
        msg.add("dddd0");

        try {
            satelliteServ.saveMessage("", msg, 4);
            fail();
        } catch (SatelliteEmptyException e) {
            assertTrue(true);
        }
    }

    @Test
    public void GIVEN_DistanciaNegativa_WHEN_SateliteValido_THEN_Exception() throws BaseException {

        List<String> msg = new ArrayList<>();
        msg.add("asdd");
        msg.add("dddd0");

        try {
            satelliteServ.saveMessage("asd", msg, -4);
            fail();
        } catch (DistanceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void GIVEN_DistanciaNegativa_WHEN_SateliteValido_THEN_MensajeEsGuardado() throws BaseException {
        Optional<Satellite> sat = Optional.of(SatelliteBuilder.buildSatoSatellite());

        Satellite sate = SatelliteBuilder.buildSatoSatellite();
        sate.setSatelliteInfo(SatelliteInfoBuilder.build());

        when(satelliteRepo.findOne(any())).thenReturn(sat);
        when(satelliteRepo.save(any())).thenReturn(null);

        List<String> msg = new ArrayList<>();
        msg.add("asdd");
        msg.add("dddd0");

        satelliteServ.saveMessage(SATO, msg, 4);
        assertTrue(true);
    }

    @Test
    public void GIVEN_MensajesGuardados_WHEN_SeBuscanPorMensajes_THEN_mensajesObtenidos() throws BaseException {

        when(satelliteRepo.findAll()).thenReturn(SatellitesBuilder.buildSatellitesWithInfo());

        Messages msg = satelliteServ.getMessages();
        assertNotNull(msg);
    }

    @Test
    public void GIVEN_MensajesNoGuardados_WHEN_SeBuscanPorMensajes_THEN_Exception() throws BaseException {

        when(satelliteRepo.findAll()).thenReturn(SatellitesBuilder.buildSatellites());

        try {
            Messages msg = satelliteServ.getMessages();
            fail();
        }catch (NoSufficientDataException e) {
            assertTrue(true);
        }
    }

    @Test
    public void GIVEN_MensajesNoGuardadosConDosSatelites_WHEN_SeBuscanPorMensajes_THEN_Exception() throws BaseException {

        when(satelliteRepo.findAll()).thenReturn(SatellitesBuilder.buildTwoSatellites());

        try {
            Messages msg = satelliteServ.getMessages();
            fail();
        }catch (NoSufficientDataException e) {
            assertTrue(true);
        }
    }

    @Test
    public void GIVEN_SatelitesConDistancias_WHEN_SeBuscanDistancias_THEN_DistaciasDevueltas() throws BaseException {
        SatelliteMessage sat = new SatelliteMessage();
        sat.setDistance(5.0);
        SatelliteMessage sat2 = new SatelliteMessage();
        sat2.setDistance(5.0);
        SatelliteMessage sat3 = new SatelliteMessage();
        sat3.setDistance(5.0);
        List<SatelliteMessage> satsM = new ArrayList<>();

        satsM.add(sat3);
        satsM.add(sat2);
        satsM.add(sat);

        double[] dist = satelliteServ.getDistances(satsM);

        assertEquals(3, dist.length);

    }





}
