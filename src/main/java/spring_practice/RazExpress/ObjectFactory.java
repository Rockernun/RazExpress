package spring_practice.RazExpress;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_practice.RazExpress.api.ApiTemplate;
import spring_practice.RazExpress.api.ErApiExRateExtractor;
import spring_practice.RazExpress.api.SimpleApiExecutor;
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
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
