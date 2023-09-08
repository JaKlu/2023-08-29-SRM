package com.kuba.shooting.range.management.services;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    void authenticate(String login, String password);

    void logout(HttpServletRequest request);
}
