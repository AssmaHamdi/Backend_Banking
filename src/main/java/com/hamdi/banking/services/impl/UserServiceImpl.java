package com.hamdi.banking.services.impl;

import com.hamdi.banking.dto.AccountDto;
import com.hamdi.banking.dto.UserDto;
import com.hamdi.banking.models.User;
import com.hamdi.banking.repositories.AccountRepository;
import com.hamdi.banking.repositories.UserRepository;
import com.hamdi.banking.services.AccountService;
import com.hamdi.banking.services.UserService;
import com.hamdi.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final AccountService accountService;

    private final ObjectsValidator<UserDto> validator;


    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        return repository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("no user was found with the provided id:" +id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    @Override
    public Integer validateAccount(Integer id) {
       User user = repository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("no user was found with id:"+id));
       user.setActive(true);

       //create an account
        AccountDto account = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(account);
        repository.save(user);

        return user.getId();

    }

    @Override
    public Integer invalidateAccount(Integer id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no user was found with id:"+id));
        user.setActive(false);

        repository.save(user);

        return user.getId();
    }
}
