package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.entities.Person;
import br.com.fiap.locatech.locatech.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findById(Long id) {
        return this.personRepository.findById(id);
    }

    public List<Person> findAll(int size, int page) {
        int offset = (page - 1) * size;

        return this.personRepository.findAll(size, offset);
    }

    public void save(Person person) {
        var saved = this.personRepository.save(person);
        Assert.state(saved == 1, "save failed - person: " + person);
    }

    public void update(Person person, Long id) {
        var updated = this.personRepository.update(person, id);

        if (updated != 1) {
            throw new RuntimeException("person not found");
        }
    }

    public void delete(Long id) {
        var deleted = this.personRepository.delete(id);

        if (deleted != 1) {
            throw new RuntimeException("person not found");
        }
    }
}
