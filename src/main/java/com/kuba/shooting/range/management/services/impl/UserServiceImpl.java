package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.database.dao.springdata.UserDAO;
import com.kuba.shooting.range.management.exceptions.LoginAlreadyExistException;
import com.kuba.shooting.range.management.exceptions.UserValidationException;
import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.User;
import com.kuba.shooting.range.management.model.dto.ChangePassDTO;
import com.kuba.shooting.range.management.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    @Override
    public User update(User user) {
        return userDAO.save(user);
    }

    @Override
    public List<User> findAll() {
        return this.userDAO.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userDAO.findById(id);
    }

    @Override
    public void changePassword(ChangePassDTO changePassDTO) {
        if (changePassDTO.getNewPass().equals(changePassDTO.getNewPassRepeat()) &&
                changePassDTO.getUser().getPassword().equals(DigestUtils.md5Hex(changePassDTO.getOldPass()))) {
            changePassDTO.getUser().setPassword(DigestUtils.md5Hex(changePassDTO.getNewPass()));
            this.userDAO.save(changePassDTO.getUser());
        } else {
            throw new UserValidationException();
        }
    }
}