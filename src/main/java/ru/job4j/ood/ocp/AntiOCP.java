package ru.job4j.ood.ocp;

public class AntiOCP {

    public void generateWords(String word, String times) {
        if ("3".equals(times)) {
            System.out.println(String.join(" ", word, word, word));
        } else if ("5".equals(times)) {
            System.out.println(String.join(" ", word, word, word, word, word));
        }
    }



    static class PaymentProcessor {
        void processPayment(double amount) {
            CreditCardPayment creditCardPayment = new CreditCardPayment();
            creditCardPayment.pay(amount);
        }

        static class CreditCardPayment {
            void pay(double amount) {
                System.out.println("Processing credit card payment for amount: " + amount);
            }
        }
    }


    static class AnimalFeeder {
        static class Animal { }

        static class Dog extends Animal { }
        static class Cat extends Animal { }

        void feedAnimal(Animal animal) {
            if (animal instanceof Dog) {
                System.out.println("Feeding dog");
            } else if (animal instanceof Cat) {
                System.out.println("Feeding cat");
            }
        }
    }
}
