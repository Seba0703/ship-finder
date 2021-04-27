package ar.com.project.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseException extends Exception {
    private String message;
}
