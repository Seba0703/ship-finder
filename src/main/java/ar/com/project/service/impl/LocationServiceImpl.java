package ar.com.project.service.impl;

import ar.com.project.exception.BaseException;
import ar.com.project.exception.ShipNotFoundException;
import ar.com.project.geometry.Circle;
import ar.com.project.geometry.CircleCircleIntersection;
import ar.com.project.geometry.Vector2;
import ar.com.project.service.LocationService;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    public double[] getLocation(double[][] positions, double [] distances) throws BaseException {

        Circle c1 = new Circle(new Vector2(positions[0][0], positions[0][1]), distances[0]);
        Circle c2 = new Circle(new Vector2(positions[1][0], positions[1][1]), distances[1]);
        Circle c3 = new Circle(new Vector2(positions[2][0], positions[2][1]), distances[2]);

        CircleCircleIntersection intersection = new CircleCircleIntersection(c1, c2);
        CircleCircleIntersection intersection2 = new CircleCircleIntersection(c2, c3);

        Vector2[] insersections = intersection.getIntersectionPoints();
        Vector2[] insersections2 = intersection2.getIntersectionPoints();

        for(int i = 0; i < insersections.length; i++) {
            for(int j = 0; j < insersections2.length; j++) {
                if(insersections[i].equals(insersections2[j])) {
                    return insersections[i].getDouble();
                }
            }
        }

        throw new ShipNotFoundException();
    }

}
