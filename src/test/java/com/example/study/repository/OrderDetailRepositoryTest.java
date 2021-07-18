package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(valueOf(900000));

//        orderDetail.setOrderGroupId(1L); // 어떠한 장바구니에
//        orderDetail.setItemId(1L); // 어떠한 상품이..

        orderDetail.setCreatedAt(now());
        orderDetail.setCreatedBy("AdminServer");

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        assertNotNull(newOrderDetail);
    }
}
