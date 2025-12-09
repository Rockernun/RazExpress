package spring_practice.RazExpress.exrate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import org.springframework.boot.json.JsonParseException;
import spring_practice.RazExpress.api.SimpleApiExecutor;
import spring_practice.RazExpress.payment.ExRateProvider;
import tools.jackson.databind.ObjectMapper;

public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return runApiForExRate(url);
    }

    private static BigDecimal runApiForExRate(String url) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = new SimpleApiExecutor().execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return extractExRate(response);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigDecimal extractExRate(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRateData exchangeRateData = objectMapper.readValue(response, ExchangeRateData.class);
        return exchangeRateData.rates().get("KRW");
    }
}
