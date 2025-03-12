package com.eyvot.pokeapi.core.contracts.api;

import com.eyvot.pokeapi.core.dto.api.PokemonListResponse;
import com.eyvot.pokeapi.core.dto.api.PokemonDetailedResponse;


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
     * @return A basic list of Pokémon.
     */
    PokemonListResponse getPokemonList(int page);

    /**
     * Retrieves detailed information about a specific Pokémon,
     * including species details and evolution chain.
     *
     * @param id The id of the Pokémon.
     * @return A detailed Pokémon response.
     */
    PokemonDetailedResponse getPokemonDetails(int id);

}
