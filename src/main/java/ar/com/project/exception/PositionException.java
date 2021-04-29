package ar.com.project.exception;

public class PositionException extends BaseException{
    public PositionException() {
        super("No hay suficientes posiciones");
    }
}
