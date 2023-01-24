package com.formedix.coding.exercise.model;

import lombok.Data;

@Data
public class ConvertCurrencyRequest {

    private String date;
    private String sourceCurrency;
    private String targetCurrency;
    private Double amount;
}
