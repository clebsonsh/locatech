package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Vehicle;
import br.com.fiap.locatech.locatech.repositories.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("/vehicles");
        List<Vehicle> vehicles = this.vehicleRepository.findAll(page, size);

        return ResponseEntity.ok().body(vehicles);
    }
}
