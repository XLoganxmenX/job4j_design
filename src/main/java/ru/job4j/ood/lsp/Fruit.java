package ru.job4j.ood.lsp;

import java.time.LocalDateTime;
import java.util.List;

public class Fruit extends Food {

    public Fruit(int id, String name, LocalDateTime createDate, LocalDateTime expiryDate,
                 float price, double discount, List<String> components) {
        super(id, name, createDate, expiryDate, price, discount, components);
    }

    public Fruit(String name, LocalDateTime createDate, LocalDateTime expiryDate,
                 float price, double discount, List<String> components) {
        super(name, createDate, expiryDate, price, discount, components);
    }
}
