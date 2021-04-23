package ar.com.project.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asd")
public class ShipFinderServiceImpl {

    @GetMapping(value = "/{id}", produces = "application/json")
    public String home(String id) {
       return "Hello Docker World";
    }
}
