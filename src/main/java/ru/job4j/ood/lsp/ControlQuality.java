package ru.job4j.ood.lsp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;
    private static final double EXPIRY_DATE_TO_DISCOUNT = 0.75;

    public ControlQuality(Store... stores) {
        this.stores = Arrays.asList(stores);
    }

    public void reDistribute(LocalDateTime startDistributeTime) {
        List<Food> foodsFromStores = collectFoodsFromAllStores();
        for (Food food : foodsFromStores) {
            double foodPercentOfExpiryDate = findPercentOfExpiryDate(startDistributeTime, food);
            setDiscountIfNeed(food, foodPercentOfExpiryDate);
            for (Store store : stores) {
                if (store.isAcceptByExpiryDate(foodPercentOfExpiryDate)) {
                    store.add(food);
                }
            }
        }

    }

    private List<Food> collectFoodsFromAllStores() {
        List<Food> collectedFoods = new LinkedList<>();
        for (Store store : stores) {
            collectedFoods.addAll(store.findAll());
            store.clear();
        }
        return collectedFoods;
    }

    private double findPercentOfExpiryDate(LocalDateTime startDistributeTime, Food food) {
        LocalDateTime createDate = food.getCreateDate();
        LocalDateTime expiryDate = food.getExpiryDate();
        long totalDuration = ChronoUnit.MINUTES.between(createDate, expiryDate);
        long remainingDuration = ChronoUnit.MINUTES.between(startDistributeTime, expiryDate);

        return Math.abs(((double) remainingDuration / totalDuration) - 1);
    }

    private void setDiscountIfNeed(Food food, double foodPercentOfExpiryDate) {
        if (foodPercentOfExpiryDate > EXPIRY_DATE_TO_DISCOUNT
                && foodPercentOfExpiryDate < 1) {
            food.setDiscount(0.2d);
            food.setPrice(food.getPrice() * (float) (1 - food.getDiscount()));
        }
    }
}
