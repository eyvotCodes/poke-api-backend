package com.eyvot.pokeapi.core.dto.pokeapi;

import com.eyvot.pokeapi.core.dto.pokeapi.type.FlavorTextEntries;

import java.util.List;

public class SpeciesPokeapiResponse {

    private int id;
    private EvolutionChain evolution_chain;
    private List<FlavorTextEntries> flavor_text_entries;


    public int getId() {
        return id;
    }

    public EvolutionChain getEvolution_chain() {
        return evolution_chain;
    }

    public List<FlavorTextEntries> getFlavor_text_entries() {
        return flavor_text_entries;
    }


    public class EvolutionChain {
        private String url;

        public String getUrl() {
            return url;
        }
    }

}
