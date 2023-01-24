package com.formedix.coding.exercise;

import com.formedix.coding.exercise.db.model.ExchangeRateDb;
import com.formedix.coding.exercise.helper.CurrencyValueHelper;
import com.formedix.coding.exercise.model.CurrencyValueRequest;
import com.formedix.coding.exercise.service.Impl.ExchangeRateServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ExchangeRateServiceTest {

    @Spy
    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @Mock
    private CurrencyValueHelper currencyValueHelper;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_maxCurrencyValue_Return_True_when_MaxCurrencyValue_Calculated_Correct() {
        CurrencyValueRequest request = new CurrencyValueRequest();
        request.setStartDate("2023-01-03");
        request.setEndDate("2023-01-20");
        request.setCurrency("USD");

        when(currencyValueHelper.getExchangeRateData(request)).thenReturn(getExchangeRate());
        Double result = exchangeRateService.maxCurrencyValue(request);
        Assert.assertTrue(result.equals(1.0843));
    }

    @Test
    public void test_maxCurrencyValue_Return_False_when_MaxCurrencyValue_Calculated_InCorrect() {
        CurrencyValueRequest request = new CurrencyValueRequest();
        request.setStartDate("2023-01-03");
        request.setEndDate("2023-01-20");
        request.setCurrency("USD");

        when(currencyValueHelper.getExchangeRateData(request)).thenReturn(getExchangeRate());
        Double result = exchangeRateService.maxCurrencyValue(request);
        Assert.assertFalse(result.equals(1.08));
    }

    @Test
    public void test_averageCurrencyValue_Return_True_when_AverageCurrencyValue_Calculated_Correct() {
        CurrencyValueRequest request = new CurrencyValueRequest();
        request.setStartDate("2023-01-03");
        request.setEndDate("2023-01-20");
        request.setCurrency("USD");

        when(currencyValueHelper.getExchangeRateData(request)).thenReturn(getExchangeRate());
        Double result = exchangeRateService.averageCurrencyValue(request);
        Assert.assertFalse(result.equals(1.083));
    }

    @Test
    public void test_averageCurrencyValue_Return_False_when_AverageCurrencyValue_Calculated_InCorrect() {
        CurrencyValueRequest request = new CurrencyValueRequest();
        request.setStartDate("2023-01-03");
        request.setEndDate("2023-01-20");
        request.setCurrency("USD");

        when(currencyValueHelper.getExchangeRateData(request)).thenReturn(getExchangeRate());
        Double result = exchangeRateService.averageCurrencyValue(request);
        Assert.assertTrue(result.equals(1.083075));
    }

    private List<ExchangeRateDb> getExchangeRate(){
        List<ExchangeRateDb> exchangeRates = new ArrayList<>();
        ExchangeRateDb exchangeRateDay1 = new ExchangeRateDb();
        exchangeRateDay1.setDate(LocalDate.parse("2023-01-20"));
        exchangeRateDay1.setCurrencyValue(1.0826);
        exchangeRateDay1.setCurrency("USD");
        exchangeRates.add(exchangeRateDay1);
        ExchangeRateDb exchangeRateDay2 = new ExchangeRateDb();
        exchangeRateDay2.setDate(LocalDate.parse("2023-01-19"));
        exchangeRateDay2.setCurrencyValue(1.0815);
        exchangeRateDay2.setCurrency("USD");
        exchangeRates.add(exchangeRateDay2);
        ExchangeRateDb exchangeRateDay3 = new ExchangeRateDb();
        exchangeRateDay3.setDate(LocalDate.parse("2023-01-18"));
        exchangeRateDay3.setCurrencyValue(1.0839);
        exchangeRateDay3.setCurrency("USD");
        exchangeRates.add(exchangeRateDay3);
        ExchangeRateDb exchangeRateDay4 = new ExchangeRateDb();
        exchangeRateDay4.setDate(LocalDate.parse("2023-01-17"));
        exchangeRateDay4.setCurrencyValue(1.0843);
        exchangeRateDay4.setCurrency("USD");
        exchangeRates.add(exchangeRateDay4);

        return exchangeRates;
    }
}
