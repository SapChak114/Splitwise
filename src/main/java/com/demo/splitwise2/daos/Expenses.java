package com.demo.splitwise2.daos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.demo.splitwise2.daos.*;

import java.util.Date;
import java.util.Map;

@Entity
@Getter
@Setter
public class Expenses extends BaseModel{

    private Long amount;

    @ManyToMany
    @JoinColumn(name = "created_by", referencedColumnName = "id", updatable = false, insertable = false)
    private User createdBy;

    @ElementCollection
    private Map<User,Long> paidTo;

    @ElementCollection
    private Map<User,Long> paidBy;
}
