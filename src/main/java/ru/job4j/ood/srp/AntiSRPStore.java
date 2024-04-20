package ru.job4j.ood.srp;

public interface AntiSRPStore<T> {

    void save(T t);

    int count(T t);
}
