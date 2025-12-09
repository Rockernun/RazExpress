package spring_practice.RazExpress;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import spring_practice.RazExpress.api.ApiTemplate;
import spring_practice.RazExpress.api.ErApiExRateExtractor;
import spring_practice.RazExpress.api.SimpleApiExecutor;
import spring_practice.RazExpress.exrate.RestTemplateExRateProvider;
import spring_practice.RazExpress.payment.ExRateProvider;
import spring_practice.RazExpress.exrate.WebApiExRateProvider;
import spring_practice.RazExpress.payment.PaymentService;

@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
