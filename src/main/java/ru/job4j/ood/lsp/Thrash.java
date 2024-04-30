package ru.job4j.ood.lsp;

import java.util.LinkedList;
import java.util.List;

public class Thrash extends AbstractStore {
    private static final double ACCEPT_EXPIRY_DATE = 1;
    private final List<String> components = new LinkedList<>();

    public List<String> getComponents() {
        return List.copyOf(components);
    }

    @Override
    public boolean delete(int id) {
        boolean result = getStorage().containsKey(id);
        if (result) {
            Food removeFood = getStorage().get(id);
            components.addAll(removeFood.getComponents());
            getStorage().remove(id);
        }

        return result;
    }

    @Override
    public boolean isAcceptByExpiryDate(double expiryDatePercent) {
        return expiryDatePercent >= ACCEPT_EXPIRY_DATE;
    }
}
