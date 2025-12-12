package spring_practice.RazExpress.order;

import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceImplSpringTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSource;

    @Test
    void createOrder() {
        var order = orderService.createOrder("0100", BigDecimal.ONE);
        Assertions.assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders() {
        List<OrderRequest> orderReq = List.of(
                new OrderRequest("0200", BigDecimal.ONE),
                new OrderRequest("0300", BigDecimal.TEN)
        );

        var orders = orderService.createOrders(orderReq);
        Assertions.assertThat(orders).hasSize(2);
        orders.forEach(order -> Assertions.assertThat(order.getId()).isGreaterThan(0));
    }

    @Test
    void createDuplicatedOrders() {
        List<OrderRequest> orderReq = List.of(
                new OrderRequest("0300", BigDecimal.ONE),
                new OrderRequest("0300", BigDecimal.TEN)
        );

        Assertions.assertThatThrownBy(() -> orderService.createOrders(orderReq))
                .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(dataSource);
        Long count = client.sql("select count(*) from orders where number = '0300'").query(Long.class).single();
        Assertions.assertThat(count).isEqualTo(0);
    }
}
