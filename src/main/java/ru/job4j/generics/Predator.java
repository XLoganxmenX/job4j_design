package ru.job4j.generics;

public class Predator extends Animal {
    private String huntType;

    public Predator(String color, int weight, String huntType) {
        super(color, weight);
        this.huntType = huntType;
    }

    public void hunt() {

    }
}
