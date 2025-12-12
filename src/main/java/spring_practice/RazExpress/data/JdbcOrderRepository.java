package spring_practice.RazExpress.data;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import spring_practice.RazExpress.order.Order;
import spring_practice.RazExpress.order.OrderRepository;

public class JdbcOrderRepository implements OrderRepository {

    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    @PostConstruct
    void initDb() {
        jdbcClient.sql("""
            create table orders (id bigint not null, number varchar(255), totalPrice numeric(38,2), primary key (id));
            alter table if exists orders drop constraint if exists UKsft0o3ihy2jyuq8rth4o136j7;
            alter table if exists orders add constraint UKsft0o3ihy2jyuq8rth4o136j7 unique (number);
            create sequence orders_SEQ start with 1 increment by 50;
        """).update();
    }

    @Override
    public void save(Order order) {
        Long id = jdbcClient.sql("select next value for orders_SEQ").query(Long.class)
                .single();
        order.setId(id);

        jdbcClient.sql("insert into orders (number,totalPrice,id) values (?,?,?)")
                .params(order.getNumber(), order.getTotalPrice(), order.getId())
                .update();
    }
}
