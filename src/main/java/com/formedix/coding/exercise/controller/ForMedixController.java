package com.formedix.coding.exercise.controller;

import com.formedix.coding.exercise.model.ConvertCurrencyRequest;
import com.formedix.coding.exercise.model.ExchangeRate;
import com.formedix.coding.exercise.model.CurrencyValueRequest;
import com.formedix.coding.exercise.model.GenericResponse;
import com.formedix.coding.exercise.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class ForMedixController {

    ExchangeRateService exchangeRateService;

    @GetMapping(path = "/date/{date}")
    public ResponseEntity<GenericResponse<List<ExchangeRate>>> loadData(@PathVariable(name = "date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<ExchangeRate> exchangeRates = exchangeRateService.retrieveCurrenciesByDate(localDate);
        GenericResponse<List<ExchangeRate>> response = new GenericResponse<>(true, "successful currencies by date", exchangeRates);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/convert")
    public ResponseEntity<GenericResponse<Double>> convertCurrency(@Validated @RequestBody ConvertCurrencyRequest request) {
        Double convertedCurrency = exchangeRateService.convertCurrency(request);
        GenericResponse<Double> response = new GenericResponse<>(true, "successful converted currency value", convertedCurrency);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/maxCurrencyValue")
    public ResponseEntity<GenericResponse<Double>> maxCurrencyValue(@Validated @RequestBody CurrencyValueRequest request) {
        Double maxCurrencyValue = exchangeRateService.maxCurrencyValue(request);
        GenericResponse<Double> response = new GenericResponse<>(true, "successful retrieved max currency value", maxCurrencyValue);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/averageCurrencyValue")
    public ResponseEntity<GenericResponse<Double>> averageCurrencyValue(@Validated @RequestBody CurrencyValueRequest request) {
        Double averageCurrencyValue = exchangeRateService.averageCurrencyValue(request);
        GenericResponse<Double> response = new GenericResponse<>(true, "successful retrieved average currency value", averageCurrencyValue);
        return ResponseEntity.ok(response);
    }
}
