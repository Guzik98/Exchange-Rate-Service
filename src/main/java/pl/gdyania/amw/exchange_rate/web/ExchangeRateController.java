package pl.gdyania.amw.exchange_rate.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.gdyania.amw.exchange_rate.service.ExchangeRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.Locale;

@RestController
@RequestMapping()
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    private static final DecimalFormat df = new DecimalFormat("#.##");

    @RequestMapping(method = RequestMethod.GET)
    public Object convertByDateAndCurrency(
            @RequestParam("time") String time,
            @RequestParam("fromCurrency") String fromCurrency,
            @RequestParam("toCurrency") String toCurrency) {

        Double value;

        try {
            value = exchangeRateService.convert(time, fromCurrency, toCurrency);
        }catch (NullPointerException e){
            return  new ResponseEntity<>("serwis nie działa w świeta i weekedny, sprawdz wprowadzoną date", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Dnia " + time  + " kurs z " + fromCurrency.toUpperCase(Locale.ROOT) + " do " + toCurrency.toUpperCase(Locale.ROOT) + " " +  df.format(value), HttpStatus.OK);
    }
}
