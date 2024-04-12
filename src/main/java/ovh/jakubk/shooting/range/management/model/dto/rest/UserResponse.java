package ovh.jakubk.shooting.range.management.model.dto.rest;

import ovh.jakubk.shooting.range.management.model.Reservation;
import ovh.jakubk.shooting.range.management.model.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String login;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String gunLicense;
    private List<Reservation> reservations;
    private User.Role role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.birthdate = user.getBirthdate();
        this.gunLicense = user.getGunLicense();
        this.reservations = user.getReservations();
        this.role = user.getRole();
    }
}
