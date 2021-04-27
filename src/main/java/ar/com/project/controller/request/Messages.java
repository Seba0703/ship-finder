package ar.com.project.controller.request;

import ar.com.project.controller.request.model.SatelliteMessage;
import lombok.Data;

import java.util.List;

@Data
public class Messages {
    private List<SatelliteMessage> satellites;
}
