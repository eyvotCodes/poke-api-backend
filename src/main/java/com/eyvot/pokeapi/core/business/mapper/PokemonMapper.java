package com.eyvot.pokeapi.core.business.mapper;

import com.eyvot.pokeapi.config.Constants;
import com.eyvot.pokeapi.core.dto.api.PokemonDetailedResponse;
import com.eyvot.pokeapi.core.dto.api.PokemonResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.EvolutionChainPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.PokemonDetailsPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.SpeciesPokeapiResponse;
import com.eyvot.pokeapi.core.dto.pokeapi.type.Abilities;
import com.eyvot.pokeapi.core.dto.pokeapi.type.EvolutionNode;
import com.eyvot.pokeapi.core.dto.pokeapi.type.FlavorTextEntries;
import com.eyvot.pokeapi.core.dto.pokeapi.type.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PokemonMapper {

    public static PokemonResponse toPokemonResponse(PokemonDetailsPokeapiResponse details) {
        return new PokemonResponse(
                details.getId(),
                details.getName(),
                details.getSprites().getFront_default(),
                formatTypes(details.getTypes()),
                details.getWeight(),
                formatAbilities(details.getAbilities())
        );
    }

    public static PokemonDetailedResponse toPokemonDetailedResponse(
            PokemonDetailsPokeapiResponse details,
            SpeciesPokeapiResponse species,
            EvolutionChainPokeapiResponse evolutionChain) {

        return new PokemonDetailedResponse(
                details.getId(),
                details.getName(),
                details.getSprites().getFront_default(),
                formatTypes(details.getTypes()),
                details.getWeight(),
                formatAbilities(details.getAbilities()),
                extractFirstFlavorTextInEnglish(species),
                extractEvolutionNames(evolutionChain.getChain())
        );
    }


    /**
     * Formats a list of Pokémon types into a single string with a predefined delimiter.
     *
     * @param types The list of Pokémon types.
     * @return A formatted string containing all types separated by the specified delimiter.
     */
    private static String formatTypes(List<Types> types) {
        return types.stream()
                .map(typeWrapper -> typeWrapper.getType().getName())
                .collect(Collectors.joining(Constants.POKE_API_FORMATTER_DELIMITER));
    }

    /**
     * Formats a list of Pokémon abilities into a single string with a predefined delimiter.
     *
     * @param abilities The list of Pokémon abilities.
     * @return A formatted string containing all abilities separated by the specified delimiter.
     */
    private static String formatAbilities(List<Abilities> abilities) {
        return abilities.stream()
                .map(abilityWrapper -> abilityWrapper.getAbility().getName())
                .collect(Collectors.joining(Constants.POKE_API_FORMATTER_DELIMITER));
    }

    /**
     * This method filters the list of flavor text entries to find the first one that matches
     * the default language.
     *
     * The extracted text is then sanitized by replacing newline ('\n') and form feed ('\f')
     * characters with spaces for better readability.
     *
     * If no English description is found, a default message is returned from
     *
     * @param species The Pokémon specie containing flavor text entries.
     * @return A cleaned flavor text description, or a default message if none is found.
     */
    private static String extractFirstFlavorTextInEnglish(SpeciesPokeapiResponse species) {
        return species.getFlavor_text_entries().stream()
                .filter(entry -> Constants.POKE_API_DEFAULT_LANGUAGE
                        .equals(entry.getLanguage().getName()))
                .map(FlavorTextEntries::getFlavor_text)
                .map(text -> text.replace("\n", " ").replace("\f", " "))
                .findFirst()
                .orElse(Constants.POKE_API_NO_POKEMON_DESCRIPTION_TEXT);
    }

    /**
     * Extracts all possible evolution paths from the given Pokémon evolution node.
     *
     * This method generates a list of evolution routes, where each route represents
     * a distinct evolutionary path from the base Pokémon to its final form.
     *
     * The default separator is ' → ', but it is configurable from project constants.
     *
     * Example:
     * Given an evolution chain like:
     * - Bulbasaur → Ivysaur → Venusaur
     * - Eevee → Vaporeon
     * - Eevee → Jolteon
     *
     * The output would be:
     * ["bulbasaur → ivysaur → venusaur", "eevee → vaporeon", "eevee → jolteon"]
     *
     * @param node The root Pokémon evolution node.
     * @return A list of formatted evolution paths.
     */
    private static List<String> extractEvolutionNames(EvolutionNode node) {
        List<String> evolutionRoutes = new ArrayList<>();
        buildEvolutionPaths(node, "", evolutionRoutes);
        return evolutionRoutes;
    }

    /**
     * Recursively builds evolution paths for a Pokémon evolution node.
     *
     * This method constructs all possible evolution routes by appending each Pokémon's
     * name to the current evolution path. When a final evolution is reached, the path
     * is added to the list of evolution routes.
     *
     * @param node The current evolution node being processed.
     * @param currentPath The evolution path string.
     * @param evolutionRoutes The list of evolution paths.
     */
    private static void buildEvolutionPaths(EvolutionNode node, String currentPath, List<String> evolutionRoutes) {
        String newPath = currentPath.isEmpty()
                ? node.getSpecies().getName()
                : currentPath + Constants.POKE_API_FORMATTER_EVOLUTION_PATH_SEPARATOR + node.getSpecies().getName();

        if (node.getEvolves_to().isEmpty()) {
            evolutionRoutes.add(newPath);
        } else {
            for (EvolutionNode evolution : node.getEvolves_to()) {
                buildEvolutionPaths(evolution, newPath, evolutionRoutes);
            }
        }
    }

}
