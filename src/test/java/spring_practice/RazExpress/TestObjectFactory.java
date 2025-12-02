package spring_practice.RazExpress;

import java.math.BigDecimal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_practice.RazExpress.exrate.WebApiExRateProvider;
import spring_practice.RazExpress.payment.ExRateProvider;
import spring_practice.RazExpress.payment.ExRateProviderStub;
import spring_practice.RazExpress.payment.PaymentService;

@Configuration
public class TestObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }
}
