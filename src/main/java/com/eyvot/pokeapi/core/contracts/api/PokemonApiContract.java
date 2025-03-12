package com.eyvot.pokeapi.core.contracts.api;

import com.eyvot.pokeapi.core.dto.api.PokemonDetailsResponse;
import com.eyvot.pokeapi.core.dto.api.PokemonItemResponse;

import java.util.List;

/**
 * Defines the contract for the API that exposes Pokémon data
 * to external clients, such as frontend applications.
 * This interface abstracts data transformation and provides a simplified response format.
 */
public interface PokemonApiContract {

    /**
     * Retrieves a list of Pokémon with basic details.
     * The data is formatted specifically for API consumers.
     *
     * @return A list of Pokémon.
     */
    List<PokemonItemResponse> getPokemonList();

    /**
     * Retrieves detailed information about a specific Pokémon,
     * including species details and evolution chain.
     *
     * @param id The ID of the Pokémon.
     * @return A detailed Pokémon response combining multiple data sources.
     */
    PokemonDetailsResponse getPokemonDetails(int id);

}
