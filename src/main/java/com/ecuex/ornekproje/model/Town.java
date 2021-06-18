package com.ecuex.ornekproje.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@RequiredArgsConstructor

@Table(name = "town")
@Entity(name = "Town")
public class Town {

    @Id
    @SequenceGenerator(
            name = "town_sequence",
            sequenceName = "town_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "town_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "town_name",
            nullable = false
    )
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "city_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "city_town_fk"
            )
    )
    private City city;
}
