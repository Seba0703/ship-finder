package ar.com.project.exception;

public class ObjectNotFoundException extends BaseException{
    public ObjectNotFoundException() {
        super("Objeto no encontrado");
    }
}
