package br.com.fiap.locatech.locatech.entities;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Vehicle {
    private Long id;
    private String make;
    private String model;
    private String plate;
    private int productionYear;
    private String color;
    private BigDecimal dailyRent;
}
