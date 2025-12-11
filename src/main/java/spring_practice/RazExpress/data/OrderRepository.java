package spring_practice.RazExpress.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import spring_practice.RazExpress.order.Order;

public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }
}
