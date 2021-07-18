package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.enumclass.ItemStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.study.model.enumclass.ItemStatus.REGISTERED;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ItemRepositoryTest extends StudyApplicationTests {

   @Autowired
   private ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setStatus(REGISTERED);
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2019년형 노트북입니다.");
        // item.setPrice(900000);
        item.setBrandName("삼성");
        item.setRegisteredAt(now());
        item.setCreatedAt(now());
        item.setCreatedBy("Partner01");
        // item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);
        assertNotNull(newItem);

    }

    @Test
    public void read() {
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);
        assertTrue(item.isPresent());
    }
}
