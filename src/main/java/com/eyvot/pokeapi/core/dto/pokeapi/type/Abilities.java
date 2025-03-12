package com.eyvot.pokeapi.core.dto.pokeapi.type;

public class Abilities {
    private int slot;
    private Ability ability;

    public int getSlot() {
        return slot;
    }

    public Ability getAbility() {
        return ability;
    }


    public class Ability {
        private String name;

        public String getName() {
            return name;
        }
    }
}
