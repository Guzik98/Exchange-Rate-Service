package pl.gdyania.amw.exchange_rate.data.repository;

import pl.gdyania.amw.exchange_rate.data.domain.ExchangeRateModel;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRateModel, Long> {

    ExchangeRateModel findByTimeAndToCurrency(String time, String toCurrency);
}
