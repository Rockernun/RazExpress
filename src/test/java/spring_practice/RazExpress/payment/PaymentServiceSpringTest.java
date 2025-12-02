package spring_practice.RazExpress.payment;

import java.io.IOException;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring_practice.RazExpress.ObjectFactory;
import spring_practice.RazExpress.TestObjectFactory;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

    @Autowired PaymentService paymentService;
    @Autowired ExRateProviderStub exRateProviderStub;

    @Test
    @DisplayName("prepare() 메서드가 요구 사항 3가지를 충족했는지 검증")
    void prepare() throws IOException {
        // exRate: 1000
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        Assertions.assertThat(payment.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000));
        Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));

        // exRate: 500
        exRateProviderStub.setExRate(BigDecimal.valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        Assertions.assertThat(payment2.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(500));
        Assertions.assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(5_000));
    }

    private static void getPayment(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {

    }
}
