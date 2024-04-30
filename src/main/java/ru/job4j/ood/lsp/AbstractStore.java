package ru.job4j.ood.lsp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractStore implements Store {
    private final Map<Integer, Food> foodStorage = new HashMap<>();
    private int returnId = 1;


    @Override
    public Map<Integer, Food> getStorage() {
        return foodStorage;
    }

    @Override
    public Food add(Food food) {
        food.setId(returnId++);
        getStorage().put(food.getId(), food);
        return food;
    }

    @Override
    public Food replace(int id, Food food) {
        return getStorage().replace(id, food);
    }

    @Override
    public boolean delete(int id) {
        return getStorage().remove(id) != null;
    }

    @Override
    public Food findById(int id) {
        return getStorage().get(id);
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(getStorage().values());
    }

    @Override
    public void clear() {
        getStorage().clear();
    }

    @Override
    public boolean isAcceptByExpiryDate(double expiryDatePercent) {
        return true;
    }
}
