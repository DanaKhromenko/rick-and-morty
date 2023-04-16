package com.gmail.danadiadius.rickandmortyapp.dto.external;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class ApiResponseDto {
    private ApiInfoDto info;
    private ApiCharacterDto[] results;
}
