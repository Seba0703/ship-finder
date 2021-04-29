package ar.com.project.exception;

public class SatelliteEmptyException extends BaseException{
    public SatelliteEmptyException() {
        super("No hay satelites");
    }
}
