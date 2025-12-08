package spring_practice.RazExpress.exrate;

import java.math.BigDecimal;
import spring_practice.RazExpress.payment.ExRateProvider;

public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {
        if (currency.equals("USD")) {
            return BigDecimal.valueOf(1000);
        }

        throw new IllegalArgumentException("지원되지 않는 통화입니다.");
    }
}
