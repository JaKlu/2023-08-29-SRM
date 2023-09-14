package com.kuba.shooting.range.management.validators;


import com.kuba.shooting.range.management.exceptions.UserValidationException;
import com.kuba.shooting.range.management.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public static void validateLogin(String login) {
        String regex = "^.{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validatePassword(String password) {
        String regex = "^.{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validateName(String name) {
        String regex = "^.{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validateSurname(String surname) {
        String regex = "^.{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(surname);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validateBirthdate(String birthdate) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(birthdate);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validateEmail(String email) {
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        String regex = "^(\\d{3}[- .]?){2}\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

/*    public static void validateGunLicense(String gunLicense) {
        String regex = "^.{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(gunLicense);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }*/


    public static void validateRole(String role) {
        boolean isValid = false;
        User.Role[] functions = User.Role.values();
        for (User.Role functionToCheck : functions) {
            if (functionToCheck.toString().equals(role)) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new UserValidationException();
        }
    }

    public static void validatePasswordEquality(String pass1, String pass2) {
        if (!pass1.equals(pass2)) {
            throw new UserValidationException();
        }
    }

    public static void validateNewUser(User user) {
        validateEditedUser(user);
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
    }

    public static void validateEditedUser(User user) {
        validateName(user.getName());
        validateSurname(user.getSurname());
        validateBirthdate(user.getBirthdate().toString());
        validateEmail(user.getEmail());
        validatePhoneNumber(user.getPhoneNumber());
        /*        validateGunLicense(user.getGunLicense());*/
        /*validateRole(String.valueOf(user.getRole()));*/
        AddressValidator.validateAddress(user.getAddress());
    }
}
