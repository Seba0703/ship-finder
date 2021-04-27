package ar.com.project.service;

import ar.com.project.exception.BaseException;
import ar.com.project.exception.ShipNotFoundException;

public interface LocationService {

    double[] getLocation(double[][] positions, double [] distances) throws BaseException;
}
