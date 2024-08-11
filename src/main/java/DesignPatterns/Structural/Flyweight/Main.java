package DesignPatterns.Structural.Flyweight;

public class Main {
    public static void main(String[] args) {
        TextDocument document = new TextDocument();

        document.addCharacter('H', "Arial", 12);
        document.addCharacter('e', "Arial", 12);
        document.addCharacter('l', "Arial", 12);
        document.addCharacter('l', "Arial", 12);
        document.addCharacter('o', "Arial", 12);
        document.addCharacter(' ', "Arial", 12);
        document.addCharacter('W', "Arial", 12);
        document.addCharacter('o', "Arial", 12);
        document.addCharacter('r', "Arial", 12);
        document.addCharacter('l', "Arial", 12);
        document.addCharacter('d', "Arial", 12);
        document.addCharacter('!', "Arial", 12);

        System.out.println("Text Document: " + document.getText());
    }
}