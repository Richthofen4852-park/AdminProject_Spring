package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.study.model.enumclass.UserStatus.REGISTERED;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {

        String account = "Test01";
        String password = "rlarkwls4852@";
        UserStatus status = REGISTERED;
        String email = "Test01@naver.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = now();
        LocalDateTime createdAt = now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        // builder pattern
        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();

        User newUser = userRepository.save(user);
        assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read() {

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("--------------?????? ?????? ----------------");
                System.out.println("????????? : " + orderGroup.getRevName());
                System.out.println("????????? : " + orderGroup.getRevAddress());
                System.out.println("?????????: " + orderGroup.getTotalPrice());
                System.out.println("?????????: " + orderGroup.getTotalQuantity());

                System.out.println("--------------?????? ?????? ----------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("???????????? ?????? : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("???????????? ???????????? : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("?????? ??????: " + orderDetail.getItem().getName());
                    System.out.println("???????????? ?????? : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("????????? ??????: " + orderDetail.getStatus());
                    System.out.println("?????? ????????????: " + orderDetail.getArrivalDate());


                });
            });
        }
        assertNotNull(user);
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
           selectUser.setAccount("pcj204");
           selectUser.setUpdatedAt(now());
           selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);

       assertFalse(deleteUser.isPresent()); // false
    }
}
