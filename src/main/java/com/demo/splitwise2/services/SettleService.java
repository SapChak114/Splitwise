package com.demo.splitwise2.services;

import com.demo.splitwise2.daos.Expenses;
import com.demo.splitwise2.daos.User;
import com.demo.splitwise2.dtos.Transactions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Record implements Comparable<Record>{
    User user;
    Long amount;


    @Override
    public int compareTo(Record o) {
        return Long.compare(this.amount,o.amount);
    }
}

@Service
public class SettleService {

    public List<Transactions> settleUp(Expenses expenses){
        Map<User, Long> paidTo = expenses.getPaidTo();
        Map<User, Long> paidBy = expenses.getPaidBy();

        Long amount = expenses.getAmount();

        Map<User,Long> expense = new HashMap<>();

        PriorityQueue<Record> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Record> min = new PriorityQueue<>();

        for(Map.Entry<User,Long> e : paidTo.entrySet()){
            if(!expense.containsKey(e.getKey())){
                expense.put(e.getKey(),0L);
            }
            expense.put(e.getKey(),expense.get(e.getKey())-e.getValue());
        }

        for(Map.Entry<User,Long> e : paidBy.entrySet()){
            if(!expense.containsKey(e.getKey())){
                expense.put(e.getKey(),0L);
            }
            expense.put(e.getKey(),expense.get(e.getKey())+e.getValue());
        }

        for(Map.Entry<User,Long> e : expense.entrySet()){
            if(e.getValue()>0){
                max.add(new Record(e.getKey(),e.getValue()));
            } else{
                min.add(new Record(e.getKey(),e.getValue()));
            }
        }

        List<Transactions> ans = new ArrayList<>();

        while(!max.isEmpty() && !min.isEmpty()){
            Record pos = max.poll();
            Record neg = min.poll();

            if(Math.abs(pos.getAmount())>Math.abs(neg.getAmount())){
                ans.add(new Transactions(neg.getUser(),pos.getUser(),Math.abs(neg.getAmount())));
                Long amountLeft = Math.abs(pos.getAmount()) - Math.abs(neg.getAmount());
                max.add(new Record(pos.getUser(),amountLeft));
            }
            else if(Math.abs(pos.getAmount())<Math.abs(neg.getAmount())){
                Long amountLeft = Math.abs(neg.getAmount()) - Math.abs(pos.getAmount());
                ans.add(new Transactions(neg.getUser(),pos.getUser(),amountLeft));
                min.add(new Record(neg.getUser(),amountLeft));
            } else{
                ans.add(new Transactions(neg.getUser(),pos.getUser(),Math.abs(neg.getAmount())));
            }
        }

        return ans;
    }
}
