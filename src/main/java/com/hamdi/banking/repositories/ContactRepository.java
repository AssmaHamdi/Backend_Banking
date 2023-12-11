package com.hamdi.banking.repositories;

import com.hamdi.banking.dto.ContactDto;
import com.hamdi.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository  extends JpaRepository<Contact, Integer> {
    List<Contact> findAllByUserId(Integer userId);
}
