package spring_practice.RazExpress.order;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class OrderServiceTxProxy implements OrderService {

    private final OrderService target;
    private final PlatformTransactionManager transactionManager;

    public OrderServiceTxProxy(OrderService target, PlatformTransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(String number, BigDecimal totalPrice) {
        return new TransactionTemplate(transactionManager).execute(status ->
                target.createOrder(number, totalPrice)
        );
    }

    @Override
    public List<Order> createOrders(List<OrderRequest> orderRequests) {
        return new TransactionTemplate(transactionManager).execute(status ->
            target.createOrders(orderRequests)
        );
    }
}
