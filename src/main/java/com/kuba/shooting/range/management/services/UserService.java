package com.kuba.shooting.range.management.services;


import com.kuba.shooting.range.management.exceptions.LoginAlreadyExistException;
import com.kuba.shooting.range.management.model.User;
import com.kuba.shooting.range.management.model.dto.ChangePassDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user) throws LoginAlreadyExistException;

    User update(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void changePassword(ChangePassDTO changePassDTO);
}
