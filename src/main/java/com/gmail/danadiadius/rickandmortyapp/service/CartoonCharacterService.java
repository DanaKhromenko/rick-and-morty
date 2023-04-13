package com.gmail.danadiadius.rickandmortyapp.service;

import com.gmail.danadiadius.rickandmortyapp.model.CartoonCharacter;
import java.util.List;

public interface CartoonCharacterService {
    void syncExternalCartoonCharacters();

    CartoonCharacter getRandomCartoonCharacter();

    List<CartoonCharacter> findAllByNameContains(String namePart);
}
