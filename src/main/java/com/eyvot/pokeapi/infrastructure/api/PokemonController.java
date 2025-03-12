package com.eyvot.pokeapi.infrastructure.api;

import com.eyvot.pokeapi.core.business.PokemonFormatterService;
import com.eyvot.pokeapi.core.contracts.api.PokemonApiContract;
import com.eyvot.pokeapi.core.dto.api.PokemonDetailedResponse;
import com.eyvot.pokeapi.core.dto.api.PokemonListResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PokemonController implements PokemonApiContract {

    private final PokemonFormatterService pokemonFormatterService;

    public PokemonController(PokemonFormatterService pokemonFormatterService) {
        this.pokemonFormatterService = pokemonFormatterService;
    }


    @Override
    @GetMapping("/pokemon")
    public PokemonListResponse getPokemonList(@RequestParam(defaultValue = "1") int page) {
        return pokemonFormatterService.getPokemonList(page);
    }

    @Override
    @GetMapping("/pokemon/{id}")
    public PokemonDetailedResponse getPokemonDetails(@PathVariable int id) {
        return pokemonFormatterService.getPokemonDetails(id);
    }

}
