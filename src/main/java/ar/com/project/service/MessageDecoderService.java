package ar.com.project.service;

import ar.com.project.dto.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MessageDecoderService {

    MessageDTO getMessage(List<List<String>> messagesSatellites);
}
