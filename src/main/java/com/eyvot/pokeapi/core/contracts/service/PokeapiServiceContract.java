package com.eyvot.pokeapi.core.contracts.service;

import com.eyvot.pokeapi.core.dto.pokeapi.EvolutionChainPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.PokemonDetailsPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.PokemonItemPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.SpeciesPokeapiResponse;

import java.util.List;

public interface PokeapiServiceContract {

    List<PokemonItemPokeapiResponse> getPokemonList(int offset, int limit);

    PokemonDetailsPokeapiResponse getPokemonDetails(int id);

    SpeciesPokeapiResponse getPokemonSpecies(int id);

    EvolutionChainPokeapiResponse getPokemonEvolutionChain(int id);

}
