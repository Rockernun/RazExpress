package spring_practice.RazExpress.order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order createOrder(String number, BigDecimal totalPrice);

    List<Order> createOrders(List<OrderRequest> reqs);
}
