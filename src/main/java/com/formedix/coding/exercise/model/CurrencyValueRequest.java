package com.formedix.coding.exercise.model;

import lombok.Data;


@Data
public class CurrencyValueRequest {

    private String startDate;
    private String endDate;
    private String currency;
}
