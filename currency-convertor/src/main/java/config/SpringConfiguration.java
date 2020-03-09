package config;

import cz.muni.fi.pa165.MeasurementAspect;
import cz.muni.fi.pa165.currency.CurrencyConvertor;
import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTable;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
//@ComponentScan(basePackages = "cz.muni.fi.pa165.currency")
public class SpringConfiguration {

    @Bean
    MeasurementAspect measurementAspect() {
        return new MeasurementAspect();
    }

    @Bean
    public ExchangeRateTable exchangeRateTable() {
        return new ExchangeRateTableImpl();
    }

    @Bean
    public CurrencyConvertor currencyConvertor(ExchangeRateTable exchangeRateTable) {
        return new CurrencyConvertorImpl(exchangeRateTable);
    }
}
