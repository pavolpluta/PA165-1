package cz.muni.fi.pa165;

import config.SpringConfiguration;
import cz.muni.fi.pa165.currency.CurrencyConvertor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainJavaConfig {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        CurrencyConvertor currencyConvertor = ctx.getBean(CurrencyConvertor.class);

        BigDecimal result = currencyConvertor.convert(Currency.getInstance("EUR"),Currency.getInstance("CZK"),BigDecimal.ONE);

        System.out.println(result);

    }
}
