package com.formedix.coding.exercise.service.Impl;

import com.formedix.coding.exercise.Repository.ExchangeRateDbRepository;
import com.formedix.coding.exercise.db.model.ExchangeRateDb;
import com.formedix.coding.exercise.config.ExceptionEnum;
import com.formedix.coding.exercise.exception.ForMedixCodingException;
import com.formedix.coding.exercise.helper.CurrencyValueHelper;
import com.formedix.coding.exercise.model.ConvertCurrencyRequest;
import com.formedix.coding.exercise.model.ExchangeRate;
import com.formedix.coding.exercise.model.CurrencyValueRequest;
import com.formedix.coding.exercise.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private ExchangeRateDbRepository exchangeRateDbRepository;
    private CurrencyValueHelper currencyValueHelper;

    /**
     * Retrieve currencies, given a specific date
     */

    @Override
    public List<ExchangeRate> retrieveCurrenciesByDate(LocalDate date) {

        try {
            List<ExchangeRateDb> exchangeRateDbs = exchangeRateDbRepository.findByDateAndCurrencyValueNotNull(date);
            return buildExchangeRate(exchangeRateDbs);

        } catch (Exception e) {
            throw new ForMedixCodingException(ExceptionEnum.ERROR_READING_FROM_DATABASE, ExceptionEnum.ERROR_READING_FROM_DATABASE.getMessage());
        }

    }

    /**
     * Given a date, source currency, target currency, and amount, it convert the currency
     */

    @Override
    public Double convertCurrency(ConvertCurrencyRequest request) {
        LocalDate date = LocalDate.parse(request.getDate());
        Double amount = request.getAmount();
        try {
            Optional<ExchangeRateDb> sourceExchangeRate = exchangeRateDbRepository.findByDateAndCurrency(date, request.getSourceCurrency());
            Optional<ExchangeRateDb> targetExchangeRate = exchangeRateDbRepository.findByDateAndCurrency(date, request.getTargetCurrency());

            if (sourceExchangeRate.isPresent() && targetExchangeRate.isPresent()) {
                return convertedCurrencyValue(sourceExchangeRate.get(), targetExchangeRate.get(), amount);
            }

        } catch (Exception e) {
            throw new ForMedixCodingException(ExceptionEnum.ERROR_READING_FROM_DATABASE, ExceptionEnum.ERROR_READING_FROM_DATABASE.getMessage());
        }

        return null;
    }

    /**
     * Given a date range and a currency, it calculate the max currency value
     */

    @Override
    public Double maxCurrencyValue(CurrencyValueRequest request) {
        try {
            List<ExchangeRateDb> exchangeRateDbs = currencyValueHelper.getExchangeRateData(request);
            return buildMaxCurrencyValue(exchangeRateDbs);

        } catch (Exception e) {
            throw new ForMedixCodingException(ExceptionEnum.ERROR_READING_FROM_DATABASE, ExceptionEnum.ERROR_READING_FROM_DATABASE.getMessage());
        }
    }

    /**
     * Given a date range and a currency, it calculate the average currency value
     */

    @Override
    public Double averageCurrencyValue(CurrencyValueRequest request) {
        try {
            List<ExchangeRateDb> exchangeRateDbs = currencyValueHelper.getExchangeRateData(request);
            return buildAverageCurrencyValue(exchangeRateDbs);

        } catch (Exception e) {
            throw new ForMedixCodingException(ExceptionEnum.ERROR_READING_FROM_DATABASE, ExceptionEnum.ERROR_READING_FROM_DATABASE.getMessage());
        }
    }

    private Double buildAverageCurrencyValue(List<ExchangeRateDb> exchangeRateDbs) {
        Double totalCurrencyValue = 0.0;
       for(ExchangeRateDb exchangeRateDb : exchangeRateDbs){
            if(exchangeRateDb.getCurrencyValue() != null) {
                totalCurrencyValue += exchangeRateDb.getCurrencyValue();
            }
       }

       return totalCurrencyValue/exchangeRateDbs.size();
    }

    private Double buildMaxCurrencyValue(List<ExchangeRateDb> exchangeRateDbs) {
        return exchangeRateDbs.stream().map(exchangeRateDb -> exchangeRateDb.getCurrencyValue())
                .filter(aDouble -> aDouble != null)
                .max(Double::compare).orElse(0.0);
    }

    private Double convertedCurrencyValue(ExchangeRateDb sourceExchangeRate, ExchangeRateDb targetExchangeRate, Double amount) {
        Double sourceCurrencyValueToEuro = amount / sourceExchangeRate.getCurrencyValue();
        return targetExchangeRate.getCurrencyValue() * sourceCurrencyValueToEuro;
    }

    private List<ExchangeRate> buildExchangeRate(List<ExchangeRateDb> exchangeRateDbs) {

        return exchangeRateDbs.stream().map(exchangeRateDb -> {
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setValue(exchangeRateDb.getCurrencyValue());
            exchangeRate.setCurrency(exchangeRateDb.getCurrency());
            exchangeRate.setDate(exchangeRateDb.getDate());

            return exchangeRate;
        }).collect(Collectors.toList());
    }
}
