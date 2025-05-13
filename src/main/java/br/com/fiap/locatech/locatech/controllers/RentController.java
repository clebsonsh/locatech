package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dtos.RentRequestDTO;
import br.com.fiap.locatech.locatech.entities.Rent;
import br.com.fiap.locatech.locatech.services.RentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rents")
public class RentController {
    private static final Logger logger = LoggerFactory.getLogger(RentController.class);

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    public ResponseEntity<List<Rent>> findAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("GET -> /rents");
        List<Rent> rents = this.rentService.findAll(size, page);

        return ResponseEntity.ok().body(rents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Rent>> findById(
            @PathVariable("id") Long id
    ) {
        logger.info("GET -> /rents/" + id);
        Optional<Rent> rent = this.rentService.findById(id);

        return ResponseEntity.ok().body(rent);
    }

    @PostMapping
    public ResponseEntity<Void> save(
            @Valid @RequestBody RentRequestDTO rent
    ) {
        logger.info("POST -> /rents");
        this.rentService.save(rent);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody RentRequestDTO rent
    ) {
        logger.info("PUT -> /rents/" + id);
        this.rentService.update(rent, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        logger.info("DELETE -> /rents/" + id);
        this.rentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
