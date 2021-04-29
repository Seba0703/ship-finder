package ar.com.project.exception;

public class EmptyMessageException extends BaseException{

    public EmptyMessageException() {
        super("Mensaje vacio");
    }
}
