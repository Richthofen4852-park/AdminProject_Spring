package com.example.study.model.entity;

import com.example.study.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderGroup"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String account;

    private String password;

    @Enumerated(STRING)
    private UserStatus status; // REGISTERED, UNREGISTERED, WAITING

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // User 1 : N OrderGroup
    @OneToMany(fetch = LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

}
