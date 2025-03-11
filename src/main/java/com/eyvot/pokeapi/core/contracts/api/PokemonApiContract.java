package com.eyvot.pokeapi.core.contracts.api;

import com.eyvot.pokeapi.core.dto.api.PokemonDetailsResponse;
import com.eyvot.pokeapi.core.dto.api.PokemonItemResponse;

import java.util.List;

public interface PokemonApiContract {

    List<PokemonItemResponse> getPokemonList();

    PokemonDetailsResponse getPokemonDetails();

}
