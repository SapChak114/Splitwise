package com.demo.splitwise2.repositories;

import com.demo.splitwise2.daos.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses,Long> {
}
