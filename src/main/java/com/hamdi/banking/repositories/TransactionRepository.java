package com.hamdi.banking.repositories;

import com.hamdi.banking.dto.TransactionSumDetail;
import com.hamdi.banking.models.Transaction;
import com.hamdi.banking.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserId(Integer userId);


    @Query("select sum(t.amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") Integer userId);

    @Query("select max(t.amount) from Transaction t where t.user.id = :userId and t.type = :transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select t.createdDate as transactionDate, sum(t.amount) from Transaction t where t.user.id = :userId and t.createdDate between :start and :end group by t.createdDate" )
    List<TransactionSumDetail> findSumTransactionsByDate(LocalDateTime start, LocalDateTime end, Integer userId);

}
