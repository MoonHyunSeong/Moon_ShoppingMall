package moon.moonShop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
public class Order {

    public Order() {

    }

    public Order(Long orderId, Member member, Delivery delivery) {
        this.orderId = orderId;
        this.member = member;
        this.delivery = delivery;
    }

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    private Delivery delivery;

    private LocalDateTime orderDate; // order time

    private OrderStatus status; // order status[ORDER, CANCEL]

}
