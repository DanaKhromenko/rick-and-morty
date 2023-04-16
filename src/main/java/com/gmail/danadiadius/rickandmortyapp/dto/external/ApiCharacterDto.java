package com.gmail.danadiadius.rickandmortyapp.dto.external;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class ApiCharacterDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
    private String species;
}
