package com.gmail.danadiadius.rickandmortyapp.controller;

import com.gmail.danadiadius.rickandmortyapp.dto.CartoonCharacterResponseDto;
import com.gmail.danadiadius.rickandmortyapp.dto.mapper.CartoonCharacterMapper;
import com.gmail.danadiadius.rickandmortyapp.model.CartoonCharacter;
import com.gmail.danadiadius.rickandmortyapp.service.CartoonCharacterService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartoon-characters")
public class CartoonCharacterController {
    private final CartoonCharacterService cartoonCharacterService;
    private final CartoonCharacterMapper mapper;

    public CartoonCharacterController(CartoonCharacterService cartoonCharacterService,
                                      CartoonCharacterMapper mapper) {
        this.cartoonCharacterService = cartoonCharacterService;
        this.mapper = mapper;
    }

    @GetMapping("/random")
    public CartoonCharacterResponseDto getRandomCartoonCharacter() {
        CartoonCharacter cartoonCharacter = cartoonCharacterService.getRandomCartoonCharacter();
        return mapper.toResponseDto(cartoonCharacter);
    }

    @GetMapping("/by-name")
    public List<CartoonCharacterResponseDto> findAllByName(
            @RequestParam("name") String namePart) {
        return cartoonCharacterService.findAllByNameContains(namePart).stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
