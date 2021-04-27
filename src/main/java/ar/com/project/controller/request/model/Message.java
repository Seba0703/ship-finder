package ar.com.project.controller.request.model;

import lombok.Data;

import java.util.List;

@Data
public class Message {
    private Double distance;
    private List<String> words;
}
