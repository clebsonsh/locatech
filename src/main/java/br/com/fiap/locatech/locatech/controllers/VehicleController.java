package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Vehicle;
import br.com.fiap.locatech.locatech.repositories.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
        logger.info("GET -> /vehicles");
        List<Vehicle> vehicles = this.vehicleRepository.findAll(page, size);

        return ResponseEntity.ok().body(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vehicle>> findById(
            @PathVariable("id") Long id
    ) {
        logger.info("GET -> /vehicles/" + id);
        Optional<Vehicle> vehicle = this.vehicleRepository.findById(id);

        return ResponseEntity.ok().body(vehicle);
    }

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody Vehicle vehicle
    ) {
        logger.info("POST -> /vehicles");
        this.vehicleRepository.save(vehicle);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Long id,
            @RequestBody Vehicle vehicle
    ) {
        logger.info("PUT -> /vehicles/" + id);
        this.vehicleRepository.update(vehicle, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        logger.info("DELETE -> /vehicles/" + id);
        this.vehicleRepository.delete(id);

        return ResponseEntity.noContent().build();
    }
}
