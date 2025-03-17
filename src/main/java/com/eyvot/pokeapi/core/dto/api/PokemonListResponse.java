package com.eyvot.pokeapi.core.dto.api;

import java.util.List;

public class PokemonListResponse {

    int pages;
    List<PokemonResponse> results;


    public PokemonListResponse(int pages, List<PokemonResponse> results) {
        this.pages = pages;
        this.results = results;
    }


    public int getPages() {
        return pages;
    }

    public List<PokemonResponse> getResults() {
        return results;
    }

}
