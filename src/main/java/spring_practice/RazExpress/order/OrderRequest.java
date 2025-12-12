package spring_practice.RazExpress.order;

import java.math.BigDecimal;

public record OrderRequest(String number, BigDecimal totalPrice) {
}
