package com.formedix.coding.exercise.service;

import com.formedix.coding.exercise.model.ConvertCurrencyRequest;
import com.formedix.coding.exercise.model.ExchangeRate;
import com.formedix.coding.exercise.model.CurrencyValueRequest;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRate> retrieveCurrenciesByDate(LocalDate date);
    Double convertCurrency(ConvertCurrencyRequest request);
    Double maxCurrencyValue(CurrencyValueRequest request);
    Double averageCurrencyValue(CurrencyValueRequest request);
}
