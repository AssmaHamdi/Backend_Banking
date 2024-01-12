package com.hamdi.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


public interface TransactionSumDetail {


   LocalDate getTransactionDate();

   BigDecimal getAmount();
}
