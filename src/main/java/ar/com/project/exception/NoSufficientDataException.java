package ar.com.project.exception;

public class NoSufficientDataException extends BaseException{
    public NoSufficientDataException() {
        super("Aun no hay suficientes datos para realizar la consulta");
    }
}
