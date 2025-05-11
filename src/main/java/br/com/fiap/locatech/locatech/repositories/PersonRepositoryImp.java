package br.com.fiap.locatech.locatech.repositories;

import br.com.fiap.locatech.locatech.entities.Person;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImp implements PersonRepository {
    private final JdbcClient jdbcClient;

    public PersonRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Person> findById(Long id) {
        String query = """
                SELECT *
                FROM people
                WHERE id = :id
                """;

        return this.jdbcClient
                .sql(query)
                .param("id", id)
                .query(Person.class)
                .optional();
    }

    @Override
    public List<Person> findAll(int size, int offset) {
        String query = """
                SELECT *
                FROM people
                LIMIT :size
                OFFSET :offset
                """;

        return this.jdbcClient
                .sql(query)
                .param("size", size)
                .param("offset", offset)
                .query(Person.class)
                .list();
    }

    @Override
    public Integer save(Person person) {
        String query = """
                INSERT INTO people (name, cpf, phone, email)
                VALUES (:name, :cpf, :phone, :email)
                """;

        return this.jdbcClient
                .sql(query)
                .param("name", person.getName())
                .param("cpf", person.getCpf())
                .param("phone", person.getPhone())
                .param("email", person.getEmail())
                .update();
    }

    @Override
    public Integer update(Person person, Long id) {
        String query = """
                UPDATE people
                SET name = :name, cpf = :cpf, phone = :phone, email = :email
                WHERE id = :id
                """;

        return this.jdbcClient
                .sql(query)
                .param("id", id)
                .param("name", person.getName())
                .param("cpf", person.getCpf())
                .param("phone", person.getPhone())
                .param("email", person.getEmail())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        String query = """
                DELETE FROM people
                WHERE id = :id
                """;

        return this.jdbcClient
                .sql(query)
                .param("id", id)
                .update();
    }
}
