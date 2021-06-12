package pl.gdyania.amw.exchange_rate.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class ExchangeRateModel  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String toCurrency;
    private String fromCurrency;
    private Double rate;
    private String time;

    public ExchangeRateModel(String time, String fromCurrency, String toCurrency, Double rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
        this.time = time;
    }

    @Override
    public String toString() {
        return   "id "+ id + " " + time +" [ from " + fromCurrency + " =  to " + toCurrency + " rate=" + rate +"] \n";
    }
}

