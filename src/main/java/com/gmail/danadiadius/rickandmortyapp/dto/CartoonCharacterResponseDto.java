package com.gmail.danadiadius.rickandmortyapp.dto;

import com.gmail.danadiadius.rickandmortyapp.model.Gender;
import com.gmail.danadiadius.rickandmortyapp.model.Status;
import lombok.Data;

@Data
public class CartoonCharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private Status status;
    private Gender gender;
    private String species;
}
