package ar.com.project.controller.response;

import ar.com.project.controller.response.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecodedMessage {
    private Position position;
    private String message;
}
