package spring_practice.RazExpress.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring_practice.RazExpress.exrate.WebApiExRateProvider;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare() 메서드가 요구 사항 3가지를 충족했는지 검증")
    void prepare() throws IOException {
        getPayment(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000));
        getPayment(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000));
        getPayment(BigDecimal.valueOf(3_000), BigDecimal.valueOf(30_000));
//        Assertions.assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        Assertions.assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private static void getPayment(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService service = new PaymentService(new ExRateProviderStub(exRate));

        Payment payment = service.prepare(1L, "USD", BigDecimal.TEN);

        Assertions.assertThat(payment.getExchangeRate()).isEqualByComparingTo(exRate);
        Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}
