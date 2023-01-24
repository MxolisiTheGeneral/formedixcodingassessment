package com.formedix.coding.exercise.service;

import com.formedix.coding.exercise.Repository.ExchangeRateDbRepository;
import com.formedix.coding.exercise.db.model.ExchangeRateDb;
import com.formedix.coding.exercise.config.ExceptionEnum;
import com.formedix.coding.exercise.exception.ForMedixCodingException;
import com.formedix.coding.exercise.model.ExchangeRate;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class DatabaseLoaderService implements ApplicationRunner {

    private DataInput dataInput;
    private ExchangeRateDbRepository rateDbRepository;

    /**
     * Reads the data from CSV file and load the data to the in memory database, when the
     * application is loaded
     */

    @Override
    public void run(ApplicationArguments args) {
        try {
         String file = "src/main/resources/eurofxref-hist.csv";
            saveHistoricalRate(file);

        } catch (Exception e) {
          throw new ForMedixCodingException(ExceptionEnum.ERROR_PERSISTING_TO_DATABASE, ExceptionEnum.ERROR_PERSISTING_TO_DATABASE.getMessage());
        }
    }

    public void saveHistoricalRate(String file) throws IOException {
        List<ExchangeRate> exchangeRates = dataInput.readHistoricalRate(file);

        for (ExchangeRate exchangeRate : exchangeRates) {
            saveData(exchangeRate);
        }
    }

    private void saveData(ExchangeRate exchangeRate) {
        ExchangeRateDb exchangeRateDb = new ExchangeRateDb();
        exchangeRateDb.setCurrency(exchangeRate.getCurrency());
        exchangeRateDb.setDate(exchangeRate.getDate());
        exchangeRateDb.setCurrencyValue(exchangeRate.getValue());

        rateDbRepository.save(exchangeRateDb);

    }

}
