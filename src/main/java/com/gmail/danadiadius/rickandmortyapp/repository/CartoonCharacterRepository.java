package com.gmail.danadiadius.rickandmortyapp.repository;

import com.gmail.danadiadius.rickandmortyapp.model.CartoonCharacter;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartoonCharacterRepository extends JpaRepository<CartoonCharacter, Long> {
    List<CartoonCharacter> getAllByExternalIdIn(Set<Long> externalIds);

    List<CartoonCharacter> findAllByNameContains(String namePart);
}
