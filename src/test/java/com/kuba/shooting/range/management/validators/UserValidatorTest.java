package com.kuba.shooting.range.management.validators;

import com.kuba.shooting.range.management.exceptions.UserValidationException;
import com.kuba.shooting.range.management.model.Address;
import com.kuba.shooting.range.management.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {
    @Test
    public void correctLoginTest() {
        String login = "login";

        UserValidator.validateLogin(login);
    }

    @Test
    public void incorrectLoginTest() {
        String login = "";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateLogin(login));
    }

    @Test
    public void correctPasswordTest() {
        String password = "password";

        UserValidator.validatePassword(password);
    }

    @Test
    public void incorrectPasswordTest() {
        String password = "";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validatePassword(password));
    }

    @Test
    public void correctNameTest() {
        String name = "name";

        UserValidator.validateName(name);
    }

    @Test
    public void incorrectNameTest() {
        String name = "";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateName(name));
    }

    @Test
    public void correctSurnameTest() {
        String surname = "surname";

        UserValidator.validateSurname(surname);
    }

    @Test
    public void incorrectSurnameTest() {
        String surname = "";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void correctBirthdateTest() {
        String birthdate = "2000-01-01";

        UserValidator.validateBirthdate(birthdate);
    }

    @Test
    public void incorrectBirthdateTest() {
        String birthdate = "asdf-sd-ds";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateBirthdate(birthdate));
    }

    @Test
    public void correctEmailTest() {
        String email = "asdasd@asdasd.qw";

        UserValidator.validateEmail(email);
    }

    @Test
    public void incorrectEmailTest() {
        String email = "asdfasdfasdsdf";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateEmail(email));
    }

    @Test
    public void correctPhoneNumberTest() {
        String phoneNumber = "123456789";

        UserValidator.validatePhoneNumber(phoneNumber);
    }

    @Test
    public void incorrectPhoneNumberTest() {
        String phoneNumber = "sggtgtrht";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    public void correctPasswordEqualityTest() {
        String password1 = "asd486asd";
        String password2 = "asd486asd";

        UserValidator.validatePasswordEquality(password1, password2);
    }

    @Test
    public void incorrectPasswordEqualityTest() {
        String password1 = "asd486asd";
        String password2 = "pokpokopko";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validatePasswordEquality(password1, password2));
    }

    @Test
    public void correctRoleTest() {
        String role = "ADMIN";

        UserValidator.validateRole(role);
    }

    @Test
    public void incorrectRoleTest() {
        String role = "DOCTOR";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateRole(role));
    }

    @Test
    public void correctNewUserTest() {
        User user = new User(2543L, "asd", "qwe", "rtfeew", "dsafsdf",
                LocalDate.of(1995, 1, 12), "weqw@dwda.ww", "456-789-123",
                new Address(81L, "Rynek 1", "12-345", "Warszawa", new User()),
                "adsasdsdaads", new ArrayList<>(),
                User.Role.USER);

        UserValidator.validateNewUser(user);
    }

    @Test
    public void incorrectNewUserTest() {
        User user = new User(2543L, "asd", "qwe", "rtfeew", "dsafsdf",
                LocalDate.of(1995, 1, 12), "weqwdwda.ww", "456-789-123",
                new Address(81L, "Rynek 1", "12-345", "Warszawa", new User()),
                "adsasdsdaads", new ArrayList<>(),
                User.Role.USER);

        Assertions.assertThrows(UserValidationException.class, () -> UserValidator.validateNewUser(user));
    }

    @Test
    public void correctEditedUserTest() {
        User user = new User(2543L, "asd", "qwe", "rtfeew", "dsafsdf",
                LocalDate.of(1995, 1, 12), "weqw@dwda.ww", "456-789-123",
                new Address(81L, "Rynek 1", "12-345", "Warszawa", new User()),
                "adsasdsdaads", new ArrayList<>(),
                User.Role.USER);

        UserValidator.validateEditedUser(user);
    }

    @Test
    public void incorrectEditedUserTest() {
        User user = new User(2543L, "asd", "qwe", "rtfeew", "dsafsdf",
                LocalDate.of(1995, 1, 12), "weqwdwda.ww", "456-789-123",
                new Address(81L, "Rynek 1", "12-345", "Warszawa", new User()),
                "adsasdsdaads", new ArrayList<>(),
                User.Role.USER);

        Assertions.assertThrows(UserValidationException.class, () -> UserValidator.validateEditedUser(user));
    }
}