package com.hamdi.banking.services;

import com.hamdi.banking.dto.TransactionDto;
import com.hamdi.banking.models.Transaction;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto>{

    List<TransactionDto> findAllByUserId(Integer userId);
}
