package com.hamdi.banking.services.impl;

import com.hamdi.banking.dto.TransactionDto;
import com.hamdi.banking.models.Transaction;
import com.hamdi.banking.models.TransactionType;
import com.hamdi.banking.repositories.TransactionRepository;
import com.hamdi.banking.services.TransactionService;
import com.hamdi.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final ObjectsValidator<TransactionDto> validator;

    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        BigDecimal TransactionMultiplier = BigDecimal.valueOf(getTransactionMutiplier(transaction.getType()));
        BigDecimal amount = transaction.getAmount().multiply(TransactionMultiplier);
        transaction.setAmount(amount);
        return repository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return repository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return repository.findById(id)
                .map(TransactionDto::fromEntity)
                .orElseThrow(() ->new EntityNotFoundException("transaction not found with id :" +id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    private int getTransactionMutiplier(TransactionType type) {

        return TransactionType.TRANSFERT==type ? -1 : 1;
    }


    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }
}
