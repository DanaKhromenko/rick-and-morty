package com.gmail.danadiadius.rickandmortyapp.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("unknown");

    private String value;
}
