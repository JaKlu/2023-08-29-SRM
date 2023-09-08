package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.database.dao.springdata.UserDAO;
import com.kuba.shooting.range.management.exceptions.LoginAlreadyExistException;
import com.kuba.shooting.range.management.model.User;
import com.kuba.shooting.range.management.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public User save(User user) throws LoginAlreadyExistException {
        if (userDAO.existsByLogin(user.getLogin())) throw new LoginAlreadyExistException();
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setRole(User.Role.USER);
        return userDAO.save(user);
    }
}