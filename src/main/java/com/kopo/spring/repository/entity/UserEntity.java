package com.kopo.spring.repository.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_INFO")
@DynamicUpdate
@DynamicInsert
@Builder
@Entity
public class UserEntity {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @NotNull
    @Column(name = "USER_NAME", length = 500, nullable = false)
    private String userName;

    @NotNull
    @Column(name = "PASSWORD", length = 300, nullable = false)
    private String password;

    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;


    @NotNull
    @Column(name = "ADDRESS1", nullable = false)
    private String address1;

    @NotNull
    @Column(name = "ADDRESS2")
    private String address2;

    @Column(name = "REG_DT")
    private String regDt;

    @Column(name = "CHG_DT")
    private String chgDT;
}
