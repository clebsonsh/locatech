package br.com.fiap.locatech.locatech.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @GetMapping
    public String getVehicles() {
        return "Hello World";
    }
}
