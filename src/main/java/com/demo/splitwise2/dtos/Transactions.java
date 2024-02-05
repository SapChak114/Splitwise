package com.demo.splitwise2.dtos;

import com.demo.splitwise2.daos.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    private User from;

    private User to;

    private Long amount;
}
