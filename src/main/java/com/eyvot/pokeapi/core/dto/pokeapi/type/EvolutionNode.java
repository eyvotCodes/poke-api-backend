package com.eyvot.pokeapi.core.dto.pokeapi.type;

import java.util.List;

public class EvolutionNode {

    private Species species;
    private List<EvolutionNode> evolves_to;


    public Species getSpecies() {
        return species;
    }

    public List<EvolutionNode> getEvolves_to() {
        return evolves_to;
    }

}
