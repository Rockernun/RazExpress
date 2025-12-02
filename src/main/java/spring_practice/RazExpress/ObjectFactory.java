package spring_practice.RazExpress;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_practice.RazExpress.exrate.CachedExRateProvider;
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
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
