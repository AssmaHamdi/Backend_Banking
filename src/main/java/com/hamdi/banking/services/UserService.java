package com.hamdi.banking.services;

import com.hamdi.banking.dto.AuthenticationRequest;
import com.hamdi.banking.dto.AuthenticationResponse;
import com.hamdi.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto dto);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
