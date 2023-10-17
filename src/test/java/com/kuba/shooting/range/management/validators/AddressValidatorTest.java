package com.kuba.shooting.range.management.validators;

import com.kuba.shooting.range.management.exceptions.AddressValidationException;
import com.kuba.shooting.range.management.model.Address;
import com.kuba.shooting.range.management.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressValidatorTest {
    @Test
    public void correctStreetValidationTest() {
        String street = "street";

        AddressValidator.validateStreet(street);
    }

    @Test
    public void incorrectStreetValidationTest() {
        String street = "";

        Assertions.assertThrows(AddressValidationException.class,
                () -> AddressValidator.validateStreet(street));
    }

    @Test
    public void correctZipCodeValidationTest() {
        String zipCode = "00-000";

        AddressValidator.validateZipCode(zipCode);
    }

    @Test
    public void incorrectZipCodeValidationTest() {
        String zipCode = "654";

        Assertions.assertThrows(AddressValidationException.class,
                () -> AddressValidator.validateZipCode(zipCode));
    }

    @Test
    public void correctCityValidationTest() {
        String city = "city";

        AddressValidator.validateCity(city);
    }

    @Test
    public void incorrectCityValidationTest() {
        String city = "";

        Assertions.assertThrows(AddressValidationException.class,
                () -> AddressValidator.validateCity(city));
    }

    @Test
    public void correctAddressValidationTest() {
        Address address = new Address(81L, "Rynek 1", "12-345", "Warszawa", new User());

        AddressValidator.validateAddress(address);
    }

    @Test
    public void incorrectAddressValidationTest() {
        Address address = new Address(81L, "", "asdsa", "", new User());

        Assertions.assertThrows(AddressValidationException.class,
                () -> AddressValidator.validateAddress(address));
    }
}