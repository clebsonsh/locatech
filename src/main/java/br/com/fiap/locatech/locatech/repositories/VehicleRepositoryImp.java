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
        String query = """
                SELECT *
                FROM vehicles
                WHERE id = :id
                """;

        return this.jdbcClient
                .sql(query)
                .param("id", id)
                .query(Vehicle.class)
                .optional();
    }

    @Override
    public List<Vehicle> findAll(int size, int offset) {
        String query = """
                SELECT *
                FROM vehicles
                LIMIT :size
                OFFSET :offset
                """;

        return this.jdbcClient
                .sql(query)
                .param("size", size)
                .param("offset", offset)
                .query(Vehicle.class)
                .list();
    }

    @Override
    public Integer save(Vehicle vehicle) {
        String query = """
                INSERT INTO vehicles (make, model, plate, production_year, color, daily_rent)
                VALUES (:make, :model, :plate, :production_year, :color, :daily_rent)
                """;
        return this.jdbcClient
                .sql(query)
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
        String query = """
                UPDATE vehicles
                SET make = :make,
                    model = :model,
                    plate = :plate,
                    production_year = :production_year,
                    color = :color,
                    daily_rent = :daily_rent
                WHERE id = :id
                """;

        return this.jdbcClient
                .sql(query)
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
        String query = """
                DELETE FROM vehicles
                WHERE id = :id
                """;

        return this.jdbcClient
                .sql(query)
                .param("id", id)
                .update();
    }
}
