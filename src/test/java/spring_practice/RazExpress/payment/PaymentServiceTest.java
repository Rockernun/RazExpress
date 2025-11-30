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
        // 준비
        PaymentService service = new PaymentService(new WebApiExRateProvider());

        // 실행
        Payment payment = service.prepare(1L, "USD", BigDecimal.TEN);

        // 검증
        Assertions.assertThat(payment.getExchangeRate()).isNotNull();
        Assertions.assertThat(payment.getConvertedAmount())
                .isEqualTo(payment.getExchangeRate().multiply(payment.getForeignCurrencyAmount()));
        Assertions.assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        Assertions.assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}
