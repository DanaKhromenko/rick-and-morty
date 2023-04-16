package com.gmail.danadiadius.rickandmortyapp.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("unknown");

    private String value;
}
