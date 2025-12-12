package spring_practice.RazExpress.order;

import java.math.BigDecimal;

public class Order {

    private Long id;
    private String number;
    private BigDecimal totalPrice;

    public Order() {}

    public Order(String number, BigDecimal totalPrice) {
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
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
