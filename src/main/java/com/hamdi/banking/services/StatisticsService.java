package com.hamdi.banking.services;

import com.hamdi.banking.dto.TransactionSumDetail;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatisticsService {

    List<TransactionSumDetail> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Integer userId);

   BigDecimal getAccountBalance(Integer userId);

    BigDecimal highestTransfert(Integer userId);

    BigDecimal highestDeposit(Integer userId);


}
