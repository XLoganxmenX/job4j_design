package ru.job4j.ood.lsp;

public class AntiLSP {

    class Bird {
        public void fly() {
            System.out.println("This bird can fly");
        }

        class Penguin extends Bird {
            @Override
            public void fly() {
                throw new UnsupportedOperationException("Penguins cannot fly");
            }
        }
    }

    class CoffeeMachine {
        public int brewCoffee(int amountOfWater) {
            if (amountOfWater < 100) {
                throw new IllegalArgumentException("Not enough water to brew coffee");
            }
            return amountOfWater - 50;
        }

        class SuperCoffeeMachine extends CoffeeMachine {
            @Override
            public int brewCoffee(int amountOfWater) {
                if (amountOfWater < 80) {
                    throw new IllegalArgumentException("Not enough water to brew coffee");
                }
                return amountOfWater - 30;
            }
        }
    }

    class MusicPlayer {

        public void playMusic(Instrument instrument) {
            if (instrument == Instrument.GUITAR) {
                System.out.println("play guitar music");
            } else if (instrument == Instrument.DRUMS) {
                System.out.println("bums!!");
            } else if (instrument == Instrument.PIANO) {
                System.out.println("lalala!!");
            }
        }

        enum Instrument {
            GUITAR, PIANO, DRUMS
        }
    }
}
