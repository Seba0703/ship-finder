package ar.com.project.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionBuilder {

    public static ResponseStatusException build(Logger LOGGER, BaseException e) {
        LOGGER.error("SERVER", e);
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage(), e);
    }

    public static ResponseStatusException build(Logger LOGGER, Exception e) {
        LOGGER.error("SERVER", e);
        return new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
}
