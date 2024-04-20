package ru.job4j.ood.srp;

public interface AntiSRP {

    void reverseWordAndSave(String word, AntiSRPStore<String> store);

    void printWordCount(String word, AntiSRPStore<String> store);
}
