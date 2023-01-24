package com.formedix.coding.exercise.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExchangeRate {

    private LocalDate date;
    private String currency;
    private Double value;
}
