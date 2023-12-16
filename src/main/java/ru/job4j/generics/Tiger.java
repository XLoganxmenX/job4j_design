package ru.job4j.generics;

public class Tiger extends Predator {
    private String pride;

    public Tiger(String color, int weight, String huntType, String pride) {
        super(color, weight, huntType);
        this.pride = pride;
    }
}
