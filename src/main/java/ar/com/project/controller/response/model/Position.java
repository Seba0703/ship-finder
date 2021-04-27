package ar.com.project.controller.response.model;

import ar.com.project.exception.PositionException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    private Double x;
    private Double y;

    public Position build(double[] pos) throws PositionException {
        if(!ArrayUtils.isEmpty(pos) && pos.length == 2) {
            setX(pos[0]);
            setY(pos[1]);
            return this;
        } else {
            throw new PositionException();
        }
    }

}
