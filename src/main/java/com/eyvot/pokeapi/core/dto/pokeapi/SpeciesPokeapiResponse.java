package com.eyvot.pokeapi.core.dto.pokeapi;

import com.eyvot.pokeapi.core.dto.pokeapi.type.FlavorTextEntries;

import java.util.List;

public class SpeciesPokeapiResponse {

    private int id;
    private List<FlavorTextEntries> flavor_text_entries;


    public int getId() {
        return id;
    }

    public List<FlavorTextEntries> getFlavor_text_entries() {
        return flavor_text_entries;
    }
}
