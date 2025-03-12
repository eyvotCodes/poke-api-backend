package com.eyvot.pokeapi.core.dto.api;

import java.util.List;

public class PokemonListResponse {

    List<PokemonResponse> results;


    public PokemonListResponse(List<PokemonResponse> results) {
        this.results = results;
    }


    public List<PokemonResponse> getResults() {
        return results;
    }

}
