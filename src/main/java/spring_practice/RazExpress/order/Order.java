package spring_practice.RazExpress.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String number;

    private BigDecimal totalPrice;

    public Order() {}

    public Order(String number, BigDecimal totalPrice) {
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
