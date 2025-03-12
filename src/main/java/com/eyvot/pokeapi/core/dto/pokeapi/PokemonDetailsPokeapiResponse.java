package com.eyvot.pokeapi.core.dto.pokeapi;

import com.eyvot.pokeapi.core.dto.pokeapi.type.Abilities;
import com.eyvot.pokeapi.core.dto.pokeapi.type.Sprites;
import com.eyvot.pokeapi.core.dto.pokeapi.type.Types;

import java.util.List;

public class PokemonDetailsPokeapiResponse {

    private int id;
    private String name;
    private int weight;
    private Sprites sprites;
    private List<Types> types;
    private List<Abilities> abilities;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public List<Types> getTypes() {
        return types;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }
}
