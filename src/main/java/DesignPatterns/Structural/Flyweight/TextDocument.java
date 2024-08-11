package DesignPatterns.Structural.Flyweight;

class TextDocument {
    private StringBuilder text = new StringBuilder();

    public void addCharacter(char character, String font, int size) {
        CharacterFlyweight flyweightCharacter = CharacterFactory.getCharacter(character, font);
        // Simulate displaying the character with the given size
        flyweightCharacter.display(size);
        text.append(character);
    }

    public String getText() {
        return text.toString();
    }
}