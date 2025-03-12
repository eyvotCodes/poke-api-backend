package com.eyvot.pokeapi.core.contracts.service;

import com.eyvot.pokeapi.core.dto.pokeapi.EvolutionChainPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.PokemonDetailsPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.PokemonItemPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.SpeciesPokeapiResponse;

import java.util.List;

/**
 * Defines the contract for interacting with the external PokéAPI service.
 * This interface abstracts the calls to PokéAPI and retrieves raw data.
 */
public interface PokeapiServiceContract {

    /**
     * Retrieves a paginated list of Pokémon from PokéAPI.
     *
     * @param offset The starting position in the Pokémon list.
     * @param limit  The number of Pokémon to retrieve.
     * @return A list of Pokémon with basic details.
     */
    List<PokemonItemPokeapiResponse> getPokemonList(int offset, int limit);

    /**
     * Retrieves detailed information about a specific Pokémon.
     *
     * @param id The ID of the Pokémon.
     * @return Detailed Pokémon data.
     */
    PokemonDetailsPokeapiResponse getPokemonDetails(int id);

    /**
     * Retrieves species-related information of a Pokémon.
     *
     * @param id The ID of the Pokémon.
     * @return Species information, including descriptions.
     */
    SpeciesPokeapiResponse getPokemonSpecies(int id);

    /**
     * Retrieves the evolution chain of a Pokémon.
     *
     * @param id The ID of the Pokémon species.
     * @return The evolution chain details.
     */
    EvolutionChainPokeapiResponse getPokemonEvolutionChain(int id);

}
