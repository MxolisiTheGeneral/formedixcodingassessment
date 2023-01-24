package com.formedix.coding.exercise.Repository;

import com.formedix.coding.exercise.db.model.ExchangeRateDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRateDbRepository extends JpaRepository<ExchangeRateDb, Long> {

    List<ExchangeRateDb> findByDateAndCurrencyValueNotNull(LocalDate date);
    Optional<ExchangeRateDb> findByDateAndCurrency(LocalDate date, String currency);
    List<ExchangeRateDb> findByCurrencyAndDateBetween(String currency, LocalDate startDate, LocalDate endDate);
}
