package ru.job4j.ood.isp;

public class AntiISP {

    interface Employee1 {

        void work();
        void stopWork();
        void talkWithStuff();
        void makeALotOfMistake();
        void cleanOffice();
        void takeACoffee();
        void sleepOnTable();
        boolean ifAngry();
        void watchYouTube();
    }


    interface Bus2 {

        void openDoor();
        void takePassengers();
        void takeTicket();
        void calculateMoney();
    }


    interface Media3 {
        void play();
        void pause();
        void stop();
        void rewind();
        void fastForward();
    }

    class MusicPlayer implements Media3 {
        public void play() {
            System.out.println("Playing music");
        }

        public void pause() {
            System.out.println("Music paused");
        }

        public void stop() {
            System.out.println("Music stopped");
        }

        public void rewind() {
            System.out.println("Rewinding music");
        }

        public void fastForward() {
            System.out.println("Fast forwarding music");
        }
    }

    class SimpleAudioPlayer implements Media3 {
        public void play() {
            System.out.println("Playing audio");
        }

        public void pause() {
            System.out.println("Audio paused");
        }

        public void stop() {
            System.out.println("Audio stopped");
        }

        public void rewind() {
            throw new UnsupportedOperationException("Rewind not supported");
        }

        public void fastForward() {
            throw new UnsupportedOperationException("Fast forward not supported");
        }
    }
}
