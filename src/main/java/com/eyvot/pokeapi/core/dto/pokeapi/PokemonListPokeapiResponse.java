package com.eyvot.pokeapi.core.dto.pokeapi;

import com.eyvot.pokeapi.core.dto.pokeapi.type.Results;

import java.util.List;


public class PokemonListPokeapiResponse {

    private int count;
    private String next;
    private String previous;
    List<Results> results;


    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Results> getResults() {
        return results;
    }

}
