package DesignPatterns.Structural.Flyweight;

import java.util.HashMap;
import java.util.Map;

class CharacterFactory {
    private static Map<String, CharacterFlyweight> characterMap = new HashMap<>();

    public static CharacterFlyweight getCharacter(char character, String font) {
        String key = character + "_" + font; // Unique key based on character and font
        if (!characterMap.containsKey(key)) {
            characterMap.put(key, new CharacterFlyweight(character, font));
            System.out.println("Creating new character flyweight: " + character);
        } else {
            System.out.println("Reusing existing character flyweight: " + character);
        }
        return characterMap.get(key);
    }
}
