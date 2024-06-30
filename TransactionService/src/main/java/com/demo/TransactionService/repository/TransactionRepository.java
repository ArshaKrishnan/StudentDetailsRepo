package com.demo.TransactionService.repository;

import com.demo.TransactionService.entity.FeeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<FeeTransaction,Long> {

}
