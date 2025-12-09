package spring_practice.RazExpress.exrate;

import java.math.BigDecimal;
import spring_practice.RazExpress.api.ApiTemplate;
import spring_practice.RazExpress.api.ErApiExRateExtractor;
import spring_practice.RazExpress.api.HttpClientApiExecutor;
import spring_practice.RazExpress.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }
}
