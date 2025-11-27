package spring_practice.RazExpress;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment1 = paymentService.prepare(1L, "USD", BigDecimal.valueOf(150.8));
        System.out.println("Payment1: " + payment1);
        System.out.println("=====================================\n");

        TimeUnit.SECONDS.sleep(1);

        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.valueOf(150.8));
        System.out.println("Payment2: " + payment2);
        System.out.println("=====================================\n");

        TimeUnit.SECONDS.sleep(2);

        Payment payment3 = paymentService.prepare(1L, "USD", BigDecimal.valueOf(150.8));
        System.out.println("Payment3: " + payment3);
    }
}

/**
 * API ExRate: 1470.151055
 * Cache Updated!
 * Payment1: Payment{orderId=1, currency='USD', foreignCurrencyAmount=150.8, exchangeRate=1470.151055, convertedAmount=221698.7790940, validUntil=2025-11-27T16:02:11.372226}
 * =====================================
 *
 * Payment2: Payment{orderId=1, currency='USD', foreignCurrencyAmount=150.8, exchangeRate=1470.151055, convertedAmount=221698.7790940, validUntil=2025-11-27T16:02:12.379576}
 * =====================================
 *
 * API ExRate: 1470.151055
 * Cache Updated!
 * Payment3: Payment{orderId=1, currency='USD', foreignCurrencyAmount=150.8, exchangeRate=1470.151055, convertedAmount=221698.7790940, validUntil=2025-11-27T16:02:14.594188}
 */