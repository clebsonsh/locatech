package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.entities.Vehicle;
import br.com.fiap.locatech.locatech.repositories.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Optional<Vehicle> findById(Long id) {
        return this.vehicleRepository.findById(id);
    }

    public List<Vehicle> findAll(int size, int page) {
        int offset = (page - 1) * size;

        return this.vehicleRepository.findAll(size, offset);
    }

    public void save(Vehicle vehicle) {
        var saved = this.vehicleRepository.save(vehicle);
        Assert.state(saved == 1, "save failed - vehicle: " + vehicle);
    }

    public void update(Vehicle vehicle, Long id) {
        var updated = this.vehicleRepository.update(vehicle, id);

        if (updated != 1) {
            throw new RuntimeException("vehicle not found");
        }
    }

    public void delete(Long id) {
        var deleted = this.vehicleRepository.delete(id);

        if (deleted != 1) {
            throw new RuntimeException("vehicle not found");
        }
    }
}
