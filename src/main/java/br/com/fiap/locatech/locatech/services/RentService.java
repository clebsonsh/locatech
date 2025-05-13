package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.RentRequestDTO;
import br.com.fiap.locatech.locatech.entities.Rent;
import br.com.fiap.locatech.locatech.repositories.RentRepository;
import br.com.fiap.locatech.locatech.repositories.VehicleRepository;
import br.com.fiap.locatech.locatech.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final VehicleRepository vehicleRepository;

    public RentService(
            RentRepository rentRepository,
            VehicleRepository vehicleRepository
    ) {
        this.rentRepository = rentRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Optional<Rent> findById(Long id) {
        return Optional.ofNullable(this.rentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rent not found")));
    }

    public List<Rent> findAll(int size, int page) {
        int offset = (page - 1) * size;

        return this.rentRepository.findAll(size, offset);
    }

    public void save(RentRequestDTO rent) {
        var rentEntity = this.createRent(rent);

        var saved = this.rentRepository.save(rentEntity);
        Assert.state(saved == 1, "save failed - rent: " + rent);
    }

    public void update(RentRequestDTO rent, Long id) {
        var rentEntity = this.createRent(rent);

        var updated = this.rentRepository.update(rentEntity, id);

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

    private Rent createRent(RentRequestDTO rentRequestDTO) {
        var vehicle = this.vehicleRepository.findById(rentRequestDTO.vehicleId())
                .orElseThrow(() -> new RuntimeException("vehicle not found"));

        var daysRented = BigDecimal.valueOf(rentRequestDTO.endedAt().getDayOfYear() - rentRequestDTO.startedAt().getDayOfYear());
        var totalRent = vehicle.getDailyRent().multiply(daysRented);

        return new Rent(
                rentRequestDTO,
                totalRent
        );
    }
}
