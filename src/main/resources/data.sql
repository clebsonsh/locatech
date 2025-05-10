CREATE TABLE vehicles
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    make            VARCHAR(255),
    model           VARCHAR(255),
    plate           VARCHAR(255),
    production_year INT,
    color           VARCHAR(255),
    daily_rent      DECIMAL(10, 2)
);

INSERT INTO vehicles (make, model, plate, production_year, color, daily_rent)
VALUES ('Chevrolet', 'Camaro', 'TMJ-2K42', 2025, 'yellow', 500.00);

CREATE TABLE people
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255),
    cpf   VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255)
);

INSERT INTO people (name, cpf, phone, email)
VALUES ('John Doe', '11122233345', '11988776655', 'john.doe@mail.com')
