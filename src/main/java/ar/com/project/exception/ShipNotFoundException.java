package ar.com.project.exception;

public class ShipNotFoundException extends BaseException{

    public ShipNotFoundException() {
        super("No se ha encontrado ninguna intersección");
    }
}
