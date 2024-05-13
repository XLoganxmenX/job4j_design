package ru.job4j.ood.dip;

import java.util.ArrayList;

public class AntiDIP {

    static class MusicStore1 {
        ArrayList<SixStringGuitar> guitars = new ArrayList<>();
    }
    static class SixStringGuitar { }

    static class Printer2 {

        private void print(String text) {
            System.out.println(text);
        }
    }

    static class ElectricPowerSwitch3 {
        private LightBulb lightBulb;

        public ElectricPowerSwitch3() {
            this.lightBulb = new LightBulb();
        }

        public void press() {
            System.out.println("Switch pressed");
            lightBulb.turnOn();
        }
    }

    static class LightBulb {
        void turnOn() {
            System.out.println("LightBulb: Bulb turned on");
        }

        void turnOff() {
            System.out.println("LightBulb: Bulb turned off");
        }
    }
}
