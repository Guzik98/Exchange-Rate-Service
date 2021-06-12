package pl.gdyania.amw.exchange_rate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gdyania.amw.exchange_rate.data.domain.ExchangeRateModel;
import pl.gdyania.amw.exchange_rate.data.repository.ExchangeRateRepository;

import java.util.Locale;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public Double convert(String time, String fromCurrency, String toCurrency) {

        double fromExchangeRateVal = 1;
        double toExchangeRateVal = 1;

        fromExchangeRateVal = getExchangeRateValue(time, fromCurrency, fromExchangeRateVal);
        toExchangeRateVal = getExchangeRateValue(time, toCurrency, toExchangeRateVal);

        return toExchangeRateVal / fromExchangeRateVal;
    }

    private double getExchangeRateValue(String time, String currency, double exchangeRateVal) {
        if (!"EUR".equalsIgnoreCase(currency)) {
            ExchangeRateModel exchangeRate = exchangeRateRepository.findByTimeAndToCurrency(time, currency.toUpperCase(Locale.ROOT));
            exchangeRateVal = exchangeRate.getRate();
        }
        return exchangeRateVal;
    }
}
