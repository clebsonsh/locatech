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