package com.kuba.shooting.range.management.model.dto.rest;

import com.kuba.shooting.range.management.model.Address;
import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String birthdate;
    private String email;
    private String phoneNumber;
    private Address address;
    private String gunLicense;
    private String role;

}
