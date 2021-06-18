package com.ecuex.ornekproje.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "CustomerEntity")
@Table(
        name = "customers"
)
public class CustomerEntity {

    @Id
    private Long tckn;
    private String name;
    private String lastName;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
}