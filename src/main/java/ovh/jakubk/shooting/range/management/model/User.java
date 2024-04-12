package ovh.jakubk.shooting.range.management.model;

import ovh.jakubk.shooting.range.management.model.dto.rest.UserRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    //@JsonIgnore
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "gun_license")
    private String gunLicense;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Reservation> reservations;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(UserRequest userRequest) {
        this.id = userRequest.getId();
        this.login = userRequest.getLogin();
        this.password = userRequest.getPassword();
        this.name = userRequest.getName();
        this.surname = userRequest.getSurname();
        this.birthdate = LocalDate.parse(userRequest.getBirthdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.email = userRequest.getEmail();
        this.phoneNumber = userRequest.getPhoneNumber();
        this.address = userRequest.getAddress();
        this.gunLicense = userRequest.getGunLicense();
        this.role = Role.valueOf(userRequest.getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gunLicense='" + gunLicense + '\'' +
                ", role=" + role +
                '}';
    }

    public enum Role {
        ADMIN("Administrator"),
        EMPLOYEE("Pracownik"),
        USER("Klient");

        private final String displayValue;

        Role(String displayValue) {
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }
}
