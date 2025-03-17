package com.eyvot.pokeapi.core.business;

import com.eyvot.pokeapi.config.Constants;
import com.eyvot.pokeapi.core.business.mapper.PokemonMapper;
import com.eyvot.pokeapi.core.contracts.service.PokeapiServiceContract;
import com.eyvot.pokeapi.core.dto.api.PokemonDetailedResponse;
import com.eyvot.pokeapi.core.dto.api.PokemonListResponse;
import com.eyvot.pokeapi.core.dto.api.PokemonResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.EvolutionChainPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.PokemonDetailsPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.PokemonListPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.SpeciesPokeapiResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PokemonFormatterService {

    private final PokeapiServiceContract pokeapiService;


    public PokemonFormatterService(PokeapiServiceContract pokeapiService) {
        this.pokeapiService = pokeapiService;
    }


    /**
     * Retrieves a paginated list of Pokémon, formatted for API response.
     *
     * This method calculates the correct offset and limit based on the provided
     * page number, making it easier for frontend clients to request paginated data
     * using a simple 'page' parameter instead of 'offset' and 'limit'.
     *
     * @param page The page number requested.
     * @return A formatted list of Pokémon with basic details.
     */
    public PokemonListResponse getPokemonList(int page) {
        int pageIndex = page == 0 ? 0 : Math.abs(page) - 1;
        int offset = pageIndex * Constants.POKE_API_POKEMON_NUMBER_PER_PAGE;
        int limit = Constants.POKE_API_POKEMON_NUMBER_PER_PAGE;

        PokemonListPokeapiResponse pokemonList = pokeapiService.getPokemonList(offset, limit);
        List<PokemonResponse> formattedList = pokemonList.getResults().stream()
                .map(pokemon -> PokemonMapper.toPokemonResponse(
                        pokeapiService.getPokemonDetails(
                                extractIdFromUrl(
                                        pokemon.getUrl()
                                )
                        )
                ))
                .toList();


        int numberOfPages = (pokemonList.getCount() / Constants.POKE_API_POKEMON_NUMBER_PER_PAGE)
                + (pokemonList.getCount() % Constants.POKE_API_POKEMON_NUMBER_PER_PAGE == 0 ? 0 : 1);
        return new PokemonListResponse(numberOfPages, formattedList);
    }

    /**
     * Retrieves detailed information about a specific Pokémon, including its species data
     * and evolution chain.
     *
     * This method fetches data from multiple endpoints in PokéAPI:
     * - Basic details
     * - Species information
     * - Evolution chain
     *
     * @param id The Pokémon id.
     * @return An object containing detailed Pokémon information.
     */
    public PokemonDetailedResponse getPokemonDetails(int id) {
        PokemonDetailsPokeapiResponse details = pokeapiService.getPokemonDetails(id);
        SpeciesPokeapiResponse species = pokeapiService.getPokemonSpecies(id);
        EvolutionChainPokeapiResponse evolutionChain = pokeapiService.getPokemonEvolutionChain(id);
        return PokemonMapper.toPokemonDetailedResponse(details, species, evolutionChain);
    }

    /**
     * Extracts the Pokémon ID from a given PokéAPI resource URL.
     *
     * This method assumes the URL follows the standard PokéAPI format, where
     * the last segment represents the ID.
     *
     * Example:
     * Given "https://pokeapi.co/api/v2/pokemon/25/", this method returns 25.
     *
     * @param url The URL from which to extract the Pokémon ID.
     * @return The extracted Pokémon ID as an integer.
     */
    private int extractIdFromUrl(String url) {
        String[] parts = url.split("/");
        return Integer.parseInt(parts[parts.length - 1]);
    }

}
