package com.ecuex.ornekproje.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "city")
@Entity(name = "City")
public class City { // edit here (index name)

    @Id
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "city_name",
            nullable = false
    )
    private String name;

    @OneToMany(
            mappedBy = "city",
            fetch = FetchType.LAZY
    )
    private List<Town> towns = new ArrayList<>();
}