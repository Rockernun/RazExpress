package spring_practice.RazExpress.exrate;

import java.math.BigDecimal;
import spring_practice.RazExpress.api.ApiTemplate;
import spring_practice.RazExpress.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExRate(url);
    }
}
