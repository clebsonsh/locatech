package br.com.fiap.locatech.locatech.repositories;

import br.com.fiap.locatech.locatech.entities.Vehicle;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryImp implements VehicleRepository {
    private final JdbcClient jdbcClient;

    public VehicleRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return this.jdbcClient
                .sql("SELECT * FROM vehicles WHERE id = :id")
                .param("id", id)
                .query(Vehicle.class)
                .optional();
    }

    @Override
    public List<Vehicle> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM vehicles LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Vehicle.class)
                .list();
    }

    @Override
    public Integer save(Vehicle vehicle) {
        return this.jdbcClient
                .sql("INSERT INTO vehicles (make, model, plate, production_year, color, daily_rent) values (:make, :model, :plate, :production_year, :color, :daily_rent)")
                .param("make", vehicle.getMake())
                .param("model", vehicle.getModel())
                .param("plate", vehicle.getPlate())
                .param("production_year", vehicle.getProductionYear())
                .param("color", vehicle.getColor())
                .param("daily_rent", vehicle.getDailyRent())
                .update();
    }

    @Override
    public Integer update(Vehicle vehicle, Long id) {
        return this.jdbcClient
                .sql("UPDATE vehicles SET make = :make, model = :model, plate = :plate, production_year = :production_year, color = :color, daily_rent = :daily_rent WHERE id = :id")
                .param("id", id)
                .param("make", vehicle.getMake())
                .param("model", vehicle.getModel())
                .param("plate", vehicle.getPlate())
                .param("production_year", vehicle.getProductionYear())
                .param("color", vehicle.getColor())
                .param("daily_rent", vehicle.getDailyRent())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM vehicles WHERE id = :id")
                .param("id", id)
                .update();
    }
}
