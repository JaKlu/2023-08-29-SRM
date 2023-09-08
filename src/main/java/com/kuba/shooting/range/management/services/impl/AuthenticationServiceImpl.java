package com.kuba.shooting.range.management.services.impl;

import com.kuba.shooting.range.management.database.dao.springdata.UserDAO;
import com.kuba.shooting.range.management.model.User;
import com.kuba.shooting.range.management.services.AuthenticationService;
import com.kuba.shooting.range.management.session.SessionData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserDAO userDAO;
    private SessionData sessionData;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = userDAO.findByLogin(login);
        if (userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            userBox.get().setPassword(null);
            this.sessionData.setUser(userBox.get());
        }
    }

    @Override
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
