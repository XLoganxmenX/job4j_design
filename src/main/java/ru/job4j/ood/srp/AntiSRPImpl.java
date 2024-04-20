package ru.job4j.ood.srp;

public class AntiSRPImpl implements AntiSRP {

    @Override
    public void reverseWordAndSave(String word, AntiSRPStore<String> store) {
        StringBuilder sb = new StringBuilder(word);
        store.save(sb.reverse().toString());
    }

    @Override
    public void printWordCount(String word, AntiSRPStore<String> store) {
        int length = store.count(word);
        int multiple = length * 2;
        System.out.println(multiple);
    }
}
