package com.formedix.coding.exercise.helper;

import com.formedix.coding.exercise.Repository.ExchangeRateDbRepository;
import com.formedix.coding.exercise.db.model.ExchangeRateDb;
import com.formedix.coding.exercise.config.ExceptionEnum;
import com.formedix.coding.exercise.exception.ForMedixCodingException;
import com.formedix.coding.exercise.model.CurrencyValueRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
@AllArgsConstructor
public class CurrencyValueHelper {

    private ExchangeRateDbRepository rateDbRepository;

    /**
     * Returns A list of exchange rate from the in memory DB if successful
     */

    public List<ExchangeRateDb> getExchangeRateData(CurrencyValueRequest request) {
        LocalDate startDate = LocalDate.parse(request.getStartDate());
        LocalDate endDate = LocalDate.parse(request.getEndDate());

        try {
            return rateDbRepository.findByCurrencyAndDateBetween(request.getCurrency(), startDate, endDate);

        } catch (Exception e) {
            throw new ForMedixCodingException(ExceptionEnum.ERROR_READING_FROM_DATABASE, ExceptionEnum.ERROR_READING_FROM_DATABASE.getMessage());
        }
    }
}
