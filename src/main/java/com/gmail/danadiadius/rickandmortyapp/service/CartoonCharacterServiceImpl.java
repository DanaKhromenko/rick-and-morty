package com.gmail.danadiadius.rickandmortyapp.service;

import com.gmail.danadiadius.rickandmortyapp.dto.external.ApiCharacterDto;
import com.gmail.danadiadius.rickandmortyapp.dto.external.ApiResponseDto;
import com.gmail.danadiadius.rickandmortyapp.dto.mapper.CartoonCharacterMapper;
import com.gmail.danadiadius.rickandmortyapp.model.CartoonCharacter;
import com.gmail.danadiadius.rickandmortyapp.repository.CartoonCharacterRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CartoonCharacterServiceImpl implements CartoonCharacterService {
    private final HttpClient httpClient;
    private final CartoonCharacterRepository cartoonCharacterRepository;
    private final CartoonCharacterMapper mapper;

    public CartoonCharacterServiceImpl(HttpClient httpClient,
                                       CartoonCharacterRepository cartoonCharacterRepository,
                                       CartoonCharacterMapper mapper) {
        this.httpClient = httpClient;
        this.cartoonCharacterRepository = cartoonCharacterRepository;
        this.mapper = mapper;
    }

    @Scheduled(cron = "0 0 11 * * ?")
    @Override
    public void syncExternalCartoonCharacters() {
        log.info("syncExternalCartoonCharacters method was invoked at " + LocalDateTime.now());

        ApiResponseDto apiResponseDto = httpClient.get("https://rickandmortyapi.com/api/character",
                ApiResponseDto.class);
        saveDtosToDB(apiResponseDto);

        while (apiResponseDto.getInfo().getNext() != null) {
            apiResponseDto = httpClient.get(apiResponseDto.getInfo().getNext(),
                    ApiResponseDto.class);
            saveDtosToDB(apiResponseDto);
        }
    }

    @Override
    public CartoonCharacter getRandomCartoonCharacter() {
        long cartoonCharactersInDB = cartoonCharacterRepository.count();
        long randomCartoonCharacterId = (long) (Math.random() * cartoonCharactersInDB);
        return cartoonCharacterRepository.getReferenceById(randomCartoonCharacterId);
    }

    @Override
    public List<CartoonCharacter> findAllByNameContains(String namePart) {
        return cartoonCharacterRepository.findAllByNameContains(namePart);
    }

    private void saveDtosToDB(ApiResponseDto apiResponseDto) {
        // Read all cartoon characters from PostgreSQL DB, whose external IDs are in response.
        Map<Long, ApiCharacterDto> externalDtos = Arrays.stream(apiResponseDto.getResults())
                .collect(Collectors.toMap(dto -> dto.getId(), Function.identity()));
        Set<Long> externalIds = externalDtos.keySet();

        List<CartoonCharacter> cartoonCharactersFromDB = cartoonCharacterRepository
                .getAllByExternalIdIn(externalIds);
        Map<Long, CartoonCharacter> cartoonCharactersWithIdsFromDB =
                cartoonCharactersFromDB.stream().collect(Collectors.toMap(
                        CartoonCharacter::getExternalId, Function.identity()));
        Set<Long> cartoonCharactersIdsFromDB = cartoonCharactersWithIdsFromDB.keySet();

        // Store cartoon character if it doesn't exist
        externalIds.removeAll(cartoonCharactersIdsFromDB);

        List<CartoonCharacter> cartoonCharactersToStore = externalIds.stream()
                .map(id -> mapper.parseApiCartoonCharacterResponseDto(externalDtos.get(id)))
                .collect(Collectors.toList());
        cartoonCharacterRepository.saveAll(cartoonCharactersToStore);
    }
}
