package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Partner;
import com.example.study.model.enumclass.PartnerStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static com.example.study.model.enumclass.PartnerStatus.REGISTERED;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PartnerRepositoryTest extends StudyApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create() {
        String name = "Partner01";
        PartnerStatus status = REGISTERED;
        String address = "서울시 강남구";
        String callCenter = "070-1111-2222";
        String partnerNumber = "010-1111-2222";
        String businessNumber = "1234567890123";
        String ceoName = "홍길동";
        LocalDateTime registeredAt = now();
        LocalDateTime createdAt = now();
        String createdBy = "AdminServer";
        Long categoryId = 1L;

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registeredAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);
        // partner.setCategoryId(categoryId);

        Partner newPartner = partnerRepository.save(partner);
        assertNotNull(newPartner);
        assertEquals(newPartner.getName(), name);
    }

    @Test
    public void read() {

    }
}
