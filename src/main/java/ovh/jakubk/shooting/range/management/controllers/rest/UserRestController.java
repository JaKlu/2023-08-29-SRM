package ovh.jakubk.shooting.range.management.controllers.rest;

import ovh.jakubk.shooting.range.management.model.User;
import ovh.jakubk.shooting.range.management.model.dto.rest.ListResponse;
import ovh.jakubk.shooting.range.management.model.dto.rest.UserResponse;
import ovh.jakubk.shooting.range.management.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserRestController {
    private UserService userService;

    @GetMapping
    public ListResponse<UserResponse> findAll() {
        return new ListResponse<>(this.userService.findAll().stream()
                .map(UserResponse::new).toList());
    }

    @GetMapping(path = "/{loginOrId}")
    public ResponseEntity<User> findByLogin(@PathVariable String loginOrId) {
        Optional<User> userBox;
        try {
            Long id = Long.parseLong(loginOrId);
            userBox = this.userService.findById(id);
        } catch (NumberFormatException e) {
            userBox = this.userService.findByLogin(loginOrId);
        }
        return userBox.map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

//    @PostMapping
//    public ResponseEntity<User> saveUser(@RequestBody User user) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }
}
