package com.eyvot.pokeapi.core.dto.api;

public class PokemonResponse {

    private int id; // direct value
    private String nombre; // direct value
    private String photoUrl; // sprite url
    private String types; // comma separated values
    private int weight; // direct value
    private String abilities; // comma separated values


    public PokemonResponse(int id, String nombre, String photoUrl, String types, int weight, String abilities) {
        this.id = id;
        this.nombre = nombre;
        this.photoUrl = photoUrl;
        this.types = types;
        this.weight = weight;
        this.abilities = abilities;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getTypes() {
        return types;
    }

    public int getWeight() {
        return weight;
    }

    public String getAbilities() {
        return abilities;
    }

}
