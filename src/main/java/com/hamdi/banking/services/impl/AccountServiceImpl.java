package com.hamdi.banking.services.impl;

import com.hamdi.banking.dto.AccountDto;
import com.hamdi.banking.exceptions.OperationNonPermittedException;
import com.hamdi.banking.models.Account;
import com.hamdi.banking.repositories.AccountRepository;
import com.hamdi.banking.services.AccountService;
import com.hamdi.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    private final ObjectsValidator<AccountDto> validator;
    @Override
    public Integer save(AccountDto dto) {
        //block account update -> iban cannot be change
       /* if (dto.getId()!=null) {
            throw new OperationNonPermittedException(
                    "Account cannot be update",
                    "Save account",
                    "Account",
                    "update not permitted"
            );
        }*/
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        if (userHasAlreadyAnAccount) {
            throw new OperationNonPermittedException(
                    "the select user has already an active account",
                    "create account ",
                    "account service",
                    "account creation "
            );
        }
        // generate random iban when creating new account else do not update iban
        if (dto.getId()==null){
        account.setIban(GenerateRandomIban());}
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll().stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("account not find with id:"+ id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    private  String GenerateRandomIban() {
        // generate an iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        //check if the iban already exists
       boolean ibanExists = repository.findByIban(iban).isPresent();
        // if exists -> generate new random iban
        if (ibanExists){
            return GenerateRandomIban();
        }
        // id not exists -> return generate iban
        return iban;
    }
}
