package com.kuba.shooting.range.management.validators;

import com.kuba.shooting.range.management.exceptions.AddressValidationException;
import com.kuba.shooting.range.management.model.Address;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressValidator {

    public static void validateStreet(String street) {
        String regex = "^.+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(street);
        if (!matcher.matches()) {
            throw new AddressValidationException();
        }
    }

    public static void validateZipCode(String zipCode) {
        String regex = "^(\\d{2}[- ]?)\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(zipCode);
        if (!matcher.matches()) {
            throw new AddressValidationException();
        }
    }

    public static void validateCity(String city) {
        String regex = "^.+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(city);
        if (!matcher.matches()) {
            throw new AddressValidationException();
        }
    }

    public static void validateAddress(Address address) {
        validateStreet(address.getStreet());
        validateZipCode(address.getZipCode());
        validateCity(address.getCity());
    }
}
