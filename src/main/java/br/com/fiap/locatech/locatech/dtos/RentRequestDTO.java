package br.com.fiap.locatech.locatech.dtos;

import java.time.LocalDate;

public record RentRequestDTO(
        Long personId,
        Long vehicleId,
        LocalDate startedAt,
        LocalDate endedAt
) {
}
