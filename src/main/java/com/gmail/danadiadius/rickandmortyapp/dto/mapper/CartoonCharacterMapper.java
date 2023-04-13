package com.gmail.danadiadius.rickandmortyapp.dto.mapper;

import com.gmail.danadiadius.rickandmortyapp.dto.CartoonCharacterResponseDto;
import com.gmail.danadiadius.rickandmortyapp.dto.external.ApiCharacterDto;
import com.gmail.danadiadius.rickandmortyapp.model.CartoonCharacter;
import com.gmail.danadiadius.rickandmortyapp.model.Gender;
import com.gmail.danadiadius.rickandmortyapp.model.Status;
import org.springframework.stereotype.Component;

@Component
public class CartoonCharacterMapper {
    public CartoonCharacter parseApiCartoonCharacterResponseDto(ApiCharacterDto dto) {
        CartoonCharacter cartoonCharacter = new CartoonCharacter();
        cartoonCharacter.setExternalId(dto.getId());
        cartoonCharacter.setName(dto.getName());
        cartoonCharacter.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        cartoonCharacter.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        cartoonCharacter.setSpecies(dto.getSpecies());
        return cartoonCharacter;
    }

    public CartoonCharacterResponseDto toResponseDto(CartoonCharacter cartoonCharacter) {
        CartoonCharacterResponseDto dto = new CartoonCharacterResponseDto();
        dto.setId(cartoonCharacter.getId());
        dto.setExternalId(cartoonCharacter.getExternalId());
        dto.setName(cartoonCharacter.getName());
        dto.setStatus(cartoonCharacter.getStatus());
        dto.setGender(cartoonCharacter.getGender());
        dto.setSpecies(cartoonCharacter.getSpecies());
        return dto;
    }
}
