package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Person;
import br.com.fiap.locatech.locatech.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("GET -> /people");
        List<Person> people = this.personService.findAll(size, page);

        return ResponseEntity.ok().body(people);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> findById(
            @PathVariable("id") Long id
    ) {
        logger.info("GET -> /people/" + id);
        Optional<Person> person = this.personService.findById(id);

        return ResponseEntity.ok().body(person);
    }

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody Person person
    ) {
        logger.info("POST -> /people");
        this.personService.save(person);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Long id,
            @RequestBody Person person
    ) {
        logger.info("PUT -> /people/" + id);
        this.personService.update(person, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        logger.info("DELETE -> /people/" + id);
        this.personService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
