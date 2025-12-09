package spring_practice.RazExpress.api;

import java.math.BigDecimal;
import org.springframework.boot.json.JsonParseException;

public interface ExRateExtractor {
    BigDecimal extract(String response) throws JsonParseException;
}
