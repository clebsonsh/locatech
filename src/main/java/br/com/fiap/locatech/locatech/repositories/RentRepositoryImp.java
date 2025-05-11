package br.com.fiap.locatech.locatech.repositories;

import br.com.fiap.locatech.locatech.entities.Rent;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RentRepositoryImp implements RentRepository {
    private final JdbcClient jdbcClient;

    public RentRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Rent> findById(Long id) {
        String query = """
                    SELECT
                        r.id,
                        r.person_id,
                        r.vehicle_id,
                        v.model AS vehicle_model,
                        p.name AS person_name,
                        p.cpf AS person_cpf,
                        r.started_at,
                        r.ended_at,
                        r.total
                    FROM rents AS r
                    INNER JOIN vehicles AS v
                        ON r.vehicle_id = v.id
                    INNER JOIN people AS p
                        ON r.person_id = p.id
                    WHERE r.id = :id
                """;

        return this.jdbcClient
                .sql(query)
                .param("id", id)
                .query(Rent.class)
                .optional();
    }

    @Override
    public List<Rent> findAll(int size, int offset) {
        String query = """
                    SELECT
                        r.id,
                        r.person_id,
                        r.vehicle_id,
                        v.model AS vehicle_model,
                        p.name AS person_name,
                        p.cpf AS person_cpf,
                        r.started_at,
                        r.ended_at,
                        r.total
                    FROM rents AS r
                    INNER JOIN vehicles AS v
                        ON r.vehicle_id = v.id
                    INNER JOIN people AS p
                        ON r.person_id = p.id
                    LIMIT :size OFFSET :offset
                """;

        return this.jdbcClient
                .sql(query)
                .param("size", size)
                .param("offset", offset)
                .query(Rent.class)
                .list();
    }

    @Override
    public Integer save(Rent rent) {
        return this.jdbcClient
                .sql("INSERT INTO rents (person_id, vehicle_id, started_at, ended_at, total) values (:person_id, :vehicle_id, :started_at, :ended_at, :total)")
                .param("person_id", rent.getPersonId())
                .param("vehicle_id", rent.getVehicleId())
                .param("started_at", rent.getStartedAt())
                .param("ended_at", rent.getEndedAt())
                .param("total", rent.getTotal())
                .update();
    }

    @Override
    public Integer update(Rent rent, Long id) {
        return this.jdbcClient
                .sql("UPDATE rents SET person_id = :person_id, vehicle_id = :vehicle_id, started_at = :started_at, ended_at = :ended_at, total = :total WHERE id = :id")
                .param("id", id)
                .param("person_id", rent.getPersonId())
                .param("vehicle_id", rent.getVehicleId())
                .param("started_at", rent.getStartedAt())
                .param("ended_at", rent.getEndedAt())
                .param("total", rent.getTotal())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM rents WHERE id = :id")
                .param("id", id)
                .update();
    }
}
