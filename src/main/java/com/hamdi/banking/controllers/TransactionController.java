package com.hamdi.banking.controllers;

import com.hamdi.banking.dto.ContactDto;
import com.hamdi.banking.dto.TransactionDto;
import com.hamdi.banking.services.ContactService;
import com.hamdi.banking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(service.save(transactionDto));
    }
    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("transaction-id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<TransactionDto>> findAllByUserId(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void> delete(@PathVariable("transaction-id") Integer id) {
        service.delete(id);
        return ResponseEntity.accepted().build();
    }


}
