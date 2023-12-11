package com.hamdi.banking.services.impl;

import com.hamdi.banking.dto.AddressDto;
import com.hamdi.banking.models.Address;
import com.hamdi.banking.repositories.AddressRepository;
import com.hamdi.banking.services.AddressService;
import com.hamdi.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    private final ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(() ->new EntityNotFoundException("Address not found with id:" +id));

    }

    @Override
    public void delete(Integer id) {

        repository.deleteById(id);

    }
}
