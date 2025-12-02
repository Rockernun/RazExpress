package spring_practice.RazExpress.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void setUp() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
     void prepare() throws IOException {
        getPayment(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000), clock);
        getPayment(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000), clock);
        getPayment(BigDecimal.valueOf(3_000), BigDecimal.valueOf(30_000), clock);
    }

    @Test
    void validUntil() throws IOException {
        PaymentService service = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock);

        Payment payment = service.prepare(1L, "USD", BigDecimal.TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private static void getPayment(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {
        PaymentService service = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = service.prepare(1L, "USD", BigDecimal.TEN);

        Assertions.assertThat(payment.getExchangeRate()).isEqualByComparingTo(exRate);
        Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}
