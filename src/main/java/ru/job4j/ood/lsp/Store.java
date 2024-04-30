package ru.job4j.ood.lsp;

import java.util.List;
import java.util.Map;

public interface Store {
    Map<Integer, Food> getStorage();

    Food add(Food food);

    Food replace(int id, Food food);

    boolean delete(int id);

    Food findById(int id);

    List<Food> findAll();

    void clear();

    boolean isAcceptByExpiryDate(double expiryDatePercent);
}
