package com.formedix.coding.exercise.service;

import com.formedix.coding.exercise.model.ExchangeRate;

import java.io.IOException;
import java.util.List;

public interface DataInput {
    List<ExchangeRate> readHistoricalRate(String file) throws IOException;
}
