package ovh.jakubk.shooting.range.management.services;


import ovh.jakubk.shooting.range.management.exceptions.LoginAlreadyExistException;
import ovh.jakubk.shooting.range.management.model.User;
import ovh.jakubk.shooting.range.management.model.dto.ChangePassDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user) throws LoginAlreadyExistException;

    User update(User user);

    void delete(Long id);

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);

    void changePassword(ChangePassDTO changePassDTO);
}
