package ar.com.project;


import ar.com.project.ShipFinderApplication;
import ar.com.project.exception.BaseException;
import ar.com.project.service.LocationService;
import ar.com.project.service.impl.LocationServiceImpl;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import lombok.Setter;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ShipFinderApplication.class})
@ExtendWith(SpringExtension.class)
public class LocationServiceTests {

    @Autowired
    private LocationService locationService;

    @Test
    public void GIVEN_PosicionesEnX_WHEN_SeBuscaUnaInterseccion_THEN_UnaInterseccionEsEncontrada() throws BaseException {
        double[][] positions = new double[][]{{1.0, 0}, {2.0, 0}, {3.0, 0}};
        double[] distances = new double[]{1.1, 0.1, 0.9};
        double[] expectedPosition = new double[]{2.1, 0};
        double[] calculatedPosition = locationService.getLocation(positions, distances);
        for (int i = 0; i < calculatedPosition.length; i++) {
            assertEquals(expectedPosition[i], calculatedPosition[i]);
        }
    }

    @Test
    public void GIVEN_TresCirculosCon4Intersecciones_WHEN_SeBuscaUnainterseccion_THEN_UnaInterseccionEsEncontrada() throws BaseException {
        double[][] positions = new double[][]{{5.0, 7.0}, {7.0, 0.0}, {8.0, 7.0}};
        double[] distances = new double[]{2.8284271247461900976033774484194, 9.0, 2.2360679774997896964091736687313};
        double[] expectedPosition = new double[]{7.0, 9,0};
        double[] calculatedPosition = locationService.getLocation(positions, distances);
        for (int i = 0; i < calculatedPosition.length; i++) {
            assertEquals(expectedPosition[i], calculatedPosition[i]);
        }
    }




    @Test
    public void GIVEN_TresCirculosSinInterseccion_WHEN_SeBuscaUnainterseccion_THEN_UnError(){
        double[][] positions = new double[][]{{1.0, 0}, {200.0, 0}, {3000.0, 0}};
        double[] distances = new double[]{1.0, 1.0, 1.0};
        try {
             locationService.getLocation(positions, distances);
            fail();
        } catch (BaseException e) {
            assertTrue(true);
        }
    }

    @Test
    public void GIVEN_TresCirculosUnoAdentroDelOtro_WHEN_SeBuscaUnainterseccion_THEN_UnaIntersecionEsEncontrada() throws BaseException {
        double[][] positions = new double[][]{{1.0, 0}, {2.0, 0}, {3.0, 0}};
        double[] distances = new double[]{3.0, 2.0, 1.0};
        double[] expectedPosition = new double[]{4.0, 0};
        double[] calculatedPosition = locationService.getLocation(positions, distances);
        for (int i = 0; i < calculatedPosition.length; i++) {
            assertEquals(expectedPosition[i], calculatedPosition[i]);
        }
    }


    @Test
    public void GIVEN_CirculosNoSeIntersectan_WHEN_SeBuscaUnainterseccion_LaPosicionNoPuedeSerEncontrada(){
        double[][] positions = new double[][]{{-500.0, -200.0}, {100.0, -100.0}, {500.0, 100.0}};
        double[] distances = new double[]{100.0, 115.5, 142.7};
        try {
            locationService.getLocation(positions, distances);
            fail();
        } catch (BaseException e) {
            assertTrue(true);
        }
    }

    @Test
    public void GIVEN_Circuloscon5Intersecciones_WHEN_SeBuscaUnainterseccion_LaPosicionNoPuedeSerEncontrada(){
        double[][] positions = new double[][]{{1.0, 0}, {2.0, 0}, {4.0, 0}};
        double[] distances = new double[]{1, 1, 2};
        try {
            locationService.getLocation(positions, distances);
            fail();
        } catch (BaseException e) {
            assertTrue(true);
        }
    }

    @Test
    /**
     * Tambien se podria haber usado esta libreria, sumada a una validacion por
     * desigualdad triangular, para saber cuando los circulos se interceptan y cuando no.
     * */
    public void CirculosNoSeIntersectanPeroLaBibliotecaBuscaUnCentroideAproximado() throws Exception {
        double[][] positions = new double[][]{{-500.0, -200.0}, {100.0, -100.0}, {500.0, 100.0}};
        double[] distances = new double[]{100.0, 115.5, 142.7};
        double[] calculatedPosition = getLocation(positions, distances);
        assertNotNull(calculatedPosition);
    }

    /**
     * Cuando los circulos no se intercetan, el algoritmo busca el centroide del mismo para allar
     * un conjunto solucion aproximado
     * */
    public double[] getLocation(double[][] positions, double [] distances) {

        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
        NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());

        return  nSolver.solve().getPoint().toArray();
    }
}
