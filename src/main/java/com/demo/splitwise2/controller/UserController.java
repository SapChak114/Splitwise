package com.demo.splitwise2.controller;

import com.demo.splitwise2.daos.Expenses;
import com.demo.splitwise2.dtos.Transactions;
import com.demo.splitwise2.services.SettleService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/splitwise")
public class UserController {

    @Autowired
    private SettleService settleService;


    @GetMapping("/settleTransactions")
    public List<Transactions> settleUp(Expenses expenses){
        return settleService.settleUp(expenses);
    }
}
