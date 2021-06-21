package com.ecuex.ornekproje.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "Address")
public class Address {

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "customer_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @NotBlank
    private String city;
    @NotBlank
    private String town;

    public Address(String city, String town) {
        this.city = city;
        this.town = town;
    }
}