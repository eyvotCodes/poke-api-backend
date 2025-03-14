package com.eyvot.pokeapi.config;

import java.util.List;


public final class Constants {

    // PokeAPI
    public static final String POKE_API_BASE_URL = "https://pokeapi.co/api/v2";
    public static final String POKE_API_POKEMON_LIST_PATH = "/pokemon";
    public static final String POKE_API_POKEMON_DETAILS_PATH = "/pokemon/{id}";
    public static final String POKE_API_POKEMON_SPECIES_PATH = "/pokemon-species/{id}";
    public static final String POKE_API_POKEMON_EVOLUTION_CHAIN_PATH = "/evolution-chain/{id}";
    public static final String POKE_API_OFFSET_PARAM = "offset";
    public static final String POKE_API_LIMIT_PARAM = "limit";
    public static final String POKE_API_NO_POKEMON_DESCRIPTION_TEXT = "No description available.";
    public static final String POKE_API_DEFAULT_LANGUAGE = "en";
    public static final String POKE_API_FORMATTER_DELIMITER = ", ";
    public static final String POKE_API_FORMATTER_EVOLUTION_PATH_SEPARATOR = " → ";
    public static final int POKE_API_POKEMON_NUMBER_PER_PAGE = 14;

    // WebClient
    public static final int WEBCLIENT_MAX_BUFFER_SIZE = 2 * 1024 * 1024; // 2 mb

    // CORS
    public static final List<String> ALLOWED_METHODS = List.of("GET");
    public static final List<String> ALLOWED_HEADERS = List.of("Content-Type", "Authorization", "Accept");
    public static final List<String> ALLOWED_ORIGINS = List.of(
            "http://localhost:5173" // Vite development server
    );


    private Constants() {}

}
