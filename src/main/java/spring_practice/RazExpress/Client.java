package spring_practice.RazExpress;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new SimpleExRatePaymentService();
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(150.8));
        System.out.println(payment);
    }
}
