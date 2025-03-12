package com.eyvot.pokeapi.core.dto.pokeapi;

import com.eyvot.pokeapi.core.dto.pokeapi.type.EvolutionNode;

public class EvolutionChainPokeapiResponse {

    private int id;
    private EvolutionNode chain;


    public int getId() {
        return id;
    }

    public EvolutionNode getChain() {
        return chain;
    }

}
