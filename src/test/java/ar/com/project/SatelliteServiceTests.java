package ar.com.project;

import ar.com.project.builder.SatelliteBuilder;
import ar.com.project.builder.SatellitesBuilder;
import ar.com.project.dao.SatelliteRepository;
import ar.com.project.dto.SatellitesPositionDTO;
import ar.com.project.entity.Satellite;
import ar.com.project.exception.BaseException;
import ar.com.project.service.SatelliteService;
import ar.com.project.service.impl.SatelliteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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





}
