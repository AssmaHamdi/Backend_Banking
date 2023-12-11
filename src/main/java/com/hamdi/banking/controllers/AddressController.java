package com.hamdi.banking.controllers;

import com.hamdi.banking.dto.AddressDto;
import com.hamdi.banking.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AddressDto addressDto){
        return ResponseEntity.ok(service.save(addressDto));
    }
    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{address-id}")
    public ResponseEntity<AddressDto> findById(@PathVariable("address-id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(@PathVariable("address-id") Integer id) {
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
}
