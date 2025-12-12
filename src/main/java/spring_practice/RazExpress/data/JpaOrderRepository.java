package spring_practice.RazExpress.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import spring_practice.RazExpress.order.Order;
import spring_practice.RazExpress.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Order order) {
        em.persist(order);
    }
}
