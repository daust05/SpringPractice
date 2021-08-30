package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember("회원1");

        Book book = createBook("book", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order order = orderRepository.findOne(orderId);
        assertThat(order.getMember()).isEqualTo(member);
        assertThat(order.getTotalPrice()).isEqualTo(10000*orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(10 - orderCount);
     }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("1","2","3"));
        em.persist(member);
        return member;
    }

    @Test
     public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember("회원1");
        Book book = createBook("book", 10000, 10);

        int orderCount = 11;
        //when

        //then
        assertThatThrownBy(()->{
            orderService.order(member.getId(), book.getId(), orderCount);
        }).isInstanceOf(NotEnoughStockException.class);

      }
      
      @Test
      public void 주문취소() throws Exception {
          //given
          Member member = createMember("회원1");
          Book book = createBook("book", 10000, 10);
          int orderCount = 2;

          Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
          Order order = orderRepository.findOne(orderId);

          //when
          orderService.cancelOrder(orderId);

          //then
          assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
          assertThat(book.getStockQuantity()).isEqualTo(10);

       }
}