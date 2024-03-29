package com.hamdi.banking.controllers;
import com.hamdi.banking.dto.TransactionSumDetail;
import com.hamdi.banking.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor

public class StatisticsController {

    private final StatisticsService service;

    @GetMapping("/sum-by-date/{userId}")
    public ResponseEntity<List<TransactionSumDetail>>  findSumTransactionByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate endDate,
            @PathVariable Integer userId){
        return ResponseEntity.ok(service.findSumTransactionByDate(startDate, endDate, userId));
    }
    @GetMapping("/account-balance/{userId}")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable Integer userId){
        return ResponseEntity.ok(service.getAccountBalance(userId));

    }
    @GetMapping("/highest-transfert/{userId}")
    public ResponseEntity<BigDecimal>  highestTransfert(@PathVariable Integer userId){
        return ResponseEntity.ok(service.highestTransfert(userId));

    }
    @GetMapping("/highest-deposit/{userId}")
    public ResponseEntity<BigDecimal>  highestDeposit(@PathVariable Integer userId){
       return ResponseEntity.ok(service.highestDeposit(userId));
    }
}
