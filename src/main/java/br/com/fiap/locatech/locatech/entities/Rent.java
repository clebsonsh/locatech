package br.com.fiap.locatech.locatech.entities;

import br.com.fiap.locatech.locatech.dtos.RentRequestDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Rent {
    private Long id;
    private Long personId;
    private Long vehicleId;
    private String vehicleModel;
    private String personName;
    private String personCpf;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private BigDecimal total;

    public Rent(
            RentRequestDTO rentRequestDTO,
            BigDecimal total
    ) {
        this.vehicleId = rentRequestDTO.vehicleId();
        this.personId = rentRequestDTO.personId();
        this.startedAt = rentRequestDTO.startedAt();
        this.endedAt = rentRequestDTO.endedAt();
        this.total = total;
    }
}
