package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.entities.Rent;
import br.com.fiap.locatech.locatech.repositories.RentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private final RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public Optional<Rent> findById(Long id) {
        return this.rentRepository.findById(id);
    }

    public List<Rent> findAll(int size, int page) {
        int offset = (page - 1) * size;

        return this.rentRepository.findAll(size, offset);
    }

    public void save(Rent rent) {
        var saved = this.rentRepository.save(rent);
        Assert.state(saved == 1, "save failed - rent: " + rent);
    }

    public void update(Rent rent, Long id) {
        var updated = this.rentRepository.update(rent, id);

        if (updated != 1) {
            throw new RuntimeException("rent not found");
        }
    }

    public void delete(Long id) {
        var deleted = this.rentRepository.delete(id);

        if (deleted != 1) {
            throw new RuntimeException("rent not found");
        }
    }
}
