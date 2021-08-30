package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL    )
    @JoinColumn(name= "delivery_id")
    private Delivery delivery;

    private LocalDateTime localDateTime; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //==연관관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    public void setOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.getOrderItems().add(orderItem);
        }
        order.setLocalDateTime(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDER);
        return order;
    }

    //==비즈니스 로직
    /**
     * 주문 취소
     */
    public void cancel(){
        if (delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다");
        }

        this.setStatus(OrderStatus.CANCEL);
        orderItems.forEach(OrderItem::cancel);
    }

    //==조회 로직==//

    /**
     * 전체 주문가격 조회
     */
    public int getTotalPrice(){
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}