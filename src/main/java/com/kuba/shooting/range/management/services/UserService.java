package com.kuba.shooting.range.management.services;


import com.kuba.shooting.range.management.exceptions.LoginAlreadyExistException;
import com.kuba.shooting.range.management.model.User;

public interface UserService {

    User save(User user) throws LoginAlreadyExistException;
}
