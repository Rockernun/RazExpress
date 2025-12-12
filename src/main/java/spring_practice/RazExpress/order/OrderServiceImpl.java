package spring_practice.RazExpress.order;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(String number, BigDecimal totalPrice) {
        Order order = new Order(number, totalPrice);
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> createOrders(List<OrderRequest> reqs) {
        return reqs.stream().map(req -> createOrder(req.number(), req.totalPrice())).toList();
    }
}
