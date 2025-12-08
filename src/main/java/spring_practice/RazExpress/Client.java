package spring_practice.RazExpress;

import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_practice.RazExpress.payment.Payment;
import spring_practice.RazExpress.payment.PaymentService;

public class Client {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(150.8));
        System.out.println("Payment: " + payment);
    }
}
