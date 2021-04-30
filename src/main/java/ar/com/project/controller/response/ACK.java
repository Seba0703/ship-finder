package ar.com.project.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ACK {
    private String status;

    public ACK() {
        status = "OK";
    }
}
