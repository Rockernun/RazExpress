package spring_practice.RazExpress.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.boot.json.JsonParseException;
import spring_practice.RazExpress.api.ApiExecutor;
import spring_practice.RazExpress.api.ErApiExRateExtractor;
import spring_practice.RazExpress.api.ExRateExtractor;
import spring_practice.RazExpress.api.SimpleApiExecutor;
import spring_practice.RazExpress.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }
}
