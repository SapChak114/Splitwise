package com.demo.splitwise2.daos;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String firstName;

    private String lastName;

    private Long contact;

    private String email;

    private String password;
}
