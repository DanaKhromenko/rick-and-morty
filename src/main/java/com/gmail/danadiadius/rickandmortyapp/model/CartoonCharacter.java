package com.gmail.danadiadius.rickandmortyapp.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cartoon_character")
public class CartoonCharacter {
    @Id
    @GeneratedValue(generator = "cartoon_character_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cartoon_character_id_seq",
            sequenceName = "cartoon_character_id_seq",
            allocationSize = 1)
    private Long id;
    private Long externalId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String species;
}
