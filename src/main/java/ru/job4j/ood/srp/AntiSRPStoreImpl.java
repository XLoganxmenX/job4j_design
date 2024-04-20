package ru.job4j.ood.srp;

import java.util.ArrayList;
import java.util.List;

public class AntiSRPStoreImpl implements AntiSRPStore<String> {
    private final List<String> word = new ArrayList<>();

    @Override
    public void save(String s) {
        word.add(s);
    }

    @Override
    public int count(String s) {
        return s.length();
    }
}
