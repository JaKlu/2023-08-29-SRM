package ovh.jakubk.shooting.range.management.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tgun")
public class Gun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "common_name")
    private String commonName;

    @Column(name = "brand")
    private String brand;

    @Column(name = "type")
    private String type;

    @Column(name = "gauge")
    private String gauge;

    @Column(name = "year_of_prod")
    private int yearOfProd;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "certificate_no")
    private String certificateNo;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "available")
    private boolean available = true;
}