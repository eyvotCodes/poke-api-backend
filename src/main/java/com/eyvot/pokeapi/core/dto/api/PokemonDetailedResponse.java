package com.eyvot.pokeapi.core.dto.api;

import java.util.List;

public class PokemonDetailedResponse extends PokemonResponse {

    private String description; // first flavored text in english
    private List<String> evolutions; // comma separated values for each ramification


    public PokemonDetailedResponse(int id, String nombre, String photoUrl, String types, int weight, String abilities,
                                   String description, List<String> evolutions) {
        super(id, nombre, photoUrl, types, weight, abilities);
        this.description = description;
        this.evolutions = evolutions;
    }


    public String getDescription() {
        return description;
    }

    public List<String> getEvolutions() {
        return evolutions;
    }

}
