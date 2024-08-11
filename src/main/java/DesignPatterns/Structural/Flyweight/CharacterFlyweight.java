package DesignPatterns.Structural.Flyweight;

class CharacterFlyweight {
    private char character;
    private String font;

    public CharacterFlyweight(char character, String font) {
        this.character = character;
        this.font = font;
    }

    public void display(int size) {
        System.out.println("Character: " + character + ", Font: " + font + ", Size: " + size);
    }

    public char getCharacter() {
        return character;
    }

    public String getFont() {
        return font;
    }
}
