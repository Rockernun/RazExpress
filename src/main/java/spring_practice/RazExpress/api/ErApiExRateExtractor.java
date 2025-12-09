package spring_practice.RazExpress.api;

import java.math.BigDecimal;
import org.springframework.boot.json.JsonParseException;
import spring_practice.RazExpress.exrate.ExchangeRateData;
import tools.jackson.databind.ObjectMapper;

public class ErApiExRateExtractor implements ExRateExtractor {
    @Override
    public BigDecimal extract(String response) throws JsonParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRateData exchangeRateData = objectMapper.readValue(response, ExchangeRateData.class);
        return exchangeRateData.rates().get("KRW");
    }
}
