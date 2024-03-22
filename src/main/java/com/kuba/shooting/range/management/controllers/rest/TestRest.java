package com.kuba.shooting.range.management.controllers.rest;

import com.kuba.shooting.range.management.model.Gun;
import com.kuba.shooting.range.management.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class TestRest {

    @RequestMapping(path = "/test1/{param}", method = RequestMethod.GET)
    public User test1(@PathVariable String param,
                      @RequestParam String cos) {
        User user = new User();
        user.setId(123L);
        user.setLogin("kuba");
        user.setEmail("kuba@kuba.pl");
        user.setPhoneNumber("123-456-789");
        user.setRole(User.Role.USER);
        return user;
    }

    @PostMapping(path = "/test2/{param}")
    public Gun giveMeAGun(@PathVariable String param,
                          @RequestParam int a,
                          @RequestHeader String header1,
                          @RequestHeader String header2,
                          @RequestBody User user) {
        System.out.println(param);
        System.out.println(a);
        System.out.println(header1);
        System.out.println(header2);
        System.out.println(user);

        return new Gun(12L, "glock", "glock", "pistol", "9mm", 2020,
                "HH123", "ASD789", "Opis fajny", true);
    }

    @GetMapping(path = "/test3")
    public ResponseEntity<Gun> test3() {
        Gun gun = new Gun(12L, "glock", "glock", "pistol", "9mm", 2020,
                "HH123", "ASD789", "Opis fajny", true);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("header1", "wartosc1")
                .header("header2", "wartosc2")
                .body(gun);
    }

/*    @GetMapping(path = "/user")
    public List<User> getUsers(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname) {

        return new ArrayList<>();
    }

    @PostMapping(path = "/user")
    public User addUser(@RequestBody User user) {
        return new User();
    }

    @PutMapping(path = "/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return new User();
    }

    @DeleteMapping(path = "/user/{id}")
    public void deleteUser(@PathVariable int id) {

    }*/
}