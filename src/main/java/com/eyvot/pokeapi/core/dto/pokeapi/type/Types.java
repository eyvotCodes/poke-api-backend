package com.eyvot.pokeapi.core.dto.pokeapi.type;

public class Types {
    private int slot;
    private Type type;


    public int getSlot() {
        return slot;
    }

    public Type getType() {
        return type;
    }


    public class Type {
        private String name;

        public String getName() {
            return name;
        }
    }

}
