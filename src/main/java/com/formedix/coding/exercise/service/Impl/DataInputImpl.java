package com.formedix.coding.exercise.service.Impl;

import com.formedix.coding.exercise.config.ExceptionEnum;
import com.formedix.coding.exercise.exception.ForMedixCodingException;
import com.formedix.coding.exercise.model.ExchangeRate;
import com.formedix.coding.exercise.service.DataInput;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataInputImpl implements DataInput {

    /**
     * Read the data from the CSV file and store them in an ArrayList
     */

    @Override
    public List<ExchangeRate> readHistoricalRate(String file) throws IOException {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .build();

            List<String[]> allHistoricalRates = csvReader.readAll();

            String[] header = allHistoricalRates.get(0);

            for (int i = 1; i < allHistoricalRates.size(); i++) {
                String[] row = allHistoricalRates.get(i);
                LocalDate date = LocalDate.parse(row[0]);

                for (int j = 1; j < row.length; j++) {
                    ExchangeRate exchangeRate = new ExchangeRate();
                    Double value;
                    if (row[j].isEmpty()) {
                        continue;
                    }
                    if (!row[j].equalsIgnoreCase("N/A")) {
                        value = Double.parseDouble(row[j]);
                    } else {
                        value = null;
                    }

                    String currency = header[j];
                    buildExchangeRate(exchangeRate, value, currency, date);
                    exchangeRates.add(exchangeRate);
                }
            }

        } catch (Exception exception) {
            throw new ForMedixCodingException(ExceptionEnum.NO_DATA_FOUND_EXCEPTION, ExceptionEnum.NO_DATA_FOUND_EXCEPTION.getMessage());
        }
        return exchangeRates;
    }

    private void buildExchangeRate(ExchangeRate exchangeRate, Double value, String currency, LocalDate date) {
        exchangeRate.setCurrency(currency);
        exchangeRate.setValue(value);
        exchangeRate.setDate(date);
    }
}
