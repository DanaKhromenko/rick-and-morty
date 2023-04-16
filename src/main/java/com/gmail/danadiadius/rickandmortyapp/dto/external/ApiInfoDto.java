package com.gmail.danadiadius.rickandmortyapp.dto.external;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class ApiInfoDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
