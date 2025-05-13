package br.com.fiap.locatech.locatech.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RentRequestDTO(
        @NotNull
        Long personId,
        @NotNull
        Long vehicleId,
        LocalDate startedAt,
        LocalDate endedAt
) {
}
