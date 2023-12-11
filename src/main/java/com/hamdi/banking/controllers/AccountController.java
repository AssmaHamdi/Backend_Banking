package com.hamdi.banking.controllers;

import com.hamdi.banking.dto.AccountDto;
import com.hamdi.banking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AccountDto accountDto){
        return ResponseEntity.ok(service.save(accountDto));
    }
    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountDto> findById(@PathVariable("account-id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{account-id}")
    public ResponseEntity<Void> delete(@PathVariable("account-id") Integer id) {
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

}
