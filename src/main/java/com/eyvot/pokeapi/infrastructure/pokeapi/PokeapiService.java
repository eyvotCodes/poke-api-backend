package com.eyvot.pokeapi.infrastructure.pokeapi;

import com.eyvot.pokeapi.config.Constants;
import com.eyvot.pokeapi.core.contracts.service.PokeapiServiceContract;
import com.eyvot.pokeapi.core.dto.pokeapi.EvolutionChainPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.PokemonDetailsPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.PokemonListPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.SpeciesPokeapiResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class PokeapiService implements PokeapiServiceContract {

    private final WebClient webClient;


    public PokeapiService(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    @Cacheable(value = Constants.CACHE_POKEAPI_SCOPE, key = "'list_' + #offset + '_' + #limit")
    public PokemonListPokeapiResponse getPokemonList(int offset, int limit) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(Constants.POKE_API_POKEMON_LIST_PATH)
                        .queryParam(Constants.POKE_API_OFFSET_PARAM, offset)
                        .queryParam(Constants.POKE_API_LIMIT_PARAM, limit)
                        .build())
                .retrieve()
                .bodyToMono(PokemonListPokeapiResponse.class)
                .block();
    }

    @Override
    @Cacheable(value = Constants.CACHE_POKEAPI_SCOPE, key = "#id")
    public PokemonDetailsPokeapiResponse getPokemonDetails(int id) {
        return webClient.get()
                .uri(Constants.POKE_API_POKEMON_DETAILS_PATH, id)
                .retrieve()
                .bodyToMono(PokemonDetailsPokeapiResponse.class)
                .block();
    }

    @Override
    @Cacheable(value = Constants.CACHE_POKEAPI_SCOPE, key = "'species_' + #id")
    public SpeciesPokeapiResponse getPokemonSpecies(int id) {
        return webClient.get()
                .uri(Constants.POKE_API_POKEMON_SPECIES_PATH, id)
                .retrieve()
                .bodyToMono(SpeciesPokeapiResponse.class)
                .block();
    }

    @Override
    @Cacheable(value = Constants.CACHE_POKEAPI_SCOPE, key = "'evolution_' + #id")
    public EvolutionChainPokeapiResponse getPokemonEvolutionChain(int id) {
        return webClient.get()
                .uri(Constants.POKE_API_POKEMON_EVOLUTION_CHAIN_PATH, id)
                .retrieve()
                .bodyToMono(EvolutionChainPokeapiResponse.class)
                .block();
    }

}
