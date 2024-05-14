package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenExpiryDateOutThenThrash() {
        Store thrash = new Thrash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        ControlQuality controlQuality = new ControlQuality(thrash, warehouse, shop);
        LocalDateTime startDistributeTime = LocalDateTime.of(2024, 4, 1, 0, 0);
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.of(2024, 2, 1, 15, 0),
                LocalDateTime.of(2024, 3, 1, 15, 0),
                4.38F,
                0,
                List.of("Pulp")
        );
        shop.add(banana);

        controlQuality.reDistribute(startDistributeTime);

        assertThat(shop.findAll()).isEmpty();
        assertThat(warehouse.findAll()).isEmpty();
        assertThat(thrash.findAll()).hasSize(1)
                .containsExactly(banana);

    }

    @Test
    void whenExpiryDate25PercentThenShop() {
        Store thrash = new Thrash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        ControlQuality controlQuality = new ControlQuality(thrash, warehouse, shop);
        LocalDateTime startDistributeTime = LocalDateTime.of(2024, 1, 1, 6, 0);
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 1, 2, 0, 0),
                4.38F,
                0,
                List.of("Pulp")
        );
        warehouse.add(banana);

        controlQuality.reDistribute(startDistributeTime);

        assertThat(thrash.findAll()).isEmpty();
        assertThat(warehouse.findAll()).isEmpty();
        assertThat(shop.findAll()).hasSize(1)
                .containsExactly(banana);
    }

    @Test
    void whenExpiryDateLess25PercentThenWarehouse() {
        Store thrash = new Thrash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        ControlQuality controlQuality = new ControlQuality(thrash, warehouse, shop);
        LocalDateTime startDistributeTime = LocalDateTime.of(2024, 2, 1, 0, 0);
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 1, 0, 0),
                4.38F,
                0,
                List.of("Pulp")
        );
        shop.add(banana);

        controlQuality.reDistribute(startDistributeTime);

        assertThat(thrash.findAll()).isEmpty();
        assertThat(shop.findAll()).isEmpty();
        assertThat(warehouse.findAll()).hasSize(1)
                .containsExactly(banana);
    }

    @Test
    void whenFoodsInStoresAndStartDistribute() {
        Store thrash = new Thrash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        ControlQuality controlQuality = new ControlQuality(thrash, warehouse, shop);
        LocalDateTime startDistributeTime = LocalDateTime.of(2024, 2, 1, 0, 0);
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.of(2023, 1, 1, 0, 0),
                LocalDateTime.of(2023, 12, 1, 0, 0),
                4.38F,
                0,
                List.of("Pulp")
        );
        Food apple = new Fruit(
                "Apple",
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 1, 0, 0),
                2.40F,
                0,
                List.of("Pulp")
        );
        Food mandarin = new Fruit(
                "Apple",
                LocalDateTime.of(2023, 4, 1, 0, 0),
                LocalDateTime.of(2024, 4, 1, 0, 0),
                3.50F,
                0,
                List.of("Pulp")
        );
        shop.add(banana);
        shop.add(apple);
        warehouse.add(mandarin);

        controlQuality.reDistribute(startDistributeTime);

        assertThat(thrash.findAll()).isNotEmpty()
                .hasSize(1)
                .containsExactly(banana);
        assertThat(shop.findAll()).isNotEmpty()
                .hasSize(1)
                .containsExactly(mandarin);
        assertThat(warehouse.findAll()).isNotEmpty()
                .hasSize(1)
                .containsExactly(apple);
    }

    @Test
    void whenExpiryDateGreater75PercentThenSetDiscount() {
        Store thrash = new Thrash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        ControlQuality controlQuality = new ControlQuality(thrash, warehouse, shop);
        LocalDateTime startDistributeTime = LocalDateTime.of(2024, 11, 1, 0, 0);
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2025, 1, 1, 0, 0),
                1.0F,
                0,
                List.of("Pulp")
        );
        shop.add(banana);

        controlQuality.reDistribute(startDistributeTime);

        assertThat(banana.getDiscount()).isEqualTo(0.2D);
        assertThat(banana.getPrice()).isEqualTo(0.8F);
    }

    @Test
    void whenExpiryDateOutThenNoDiscount() {
        Store thrash = new Thrash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        ControlQuality controlQuality = new ControlQuality(thrash, warehouse, shop);
        LocalDateTime startDistributeTime = LocalDateTime.of(2026, 1, 1, 0, 0);
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2025, 1, 1, 0, 0),
                1.0F,
                0,
                List.of("Pulp")
        );
        shop.add(banana);

        controlQuality.reDistribute(startDistributeTime);

        assertThat(banana.getDiscount()).isEqualTo(0);
        assertThat(banana.getPrice()).isEqualTo(1.0F);
    }

    @Test
    void whenFoodsInStoresAndStartResort() {
        Store thrash = new Thrash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        ControlQuality controlQuality = new ControlQuality(thrash, warehouse, shop);
        LocalDateTime timeNow = LocalDateTime.now();
        Food banana = new Fruit(
                "Banana",
                timeNow.minusMonths(12),
                timeNow.minusMonths(6),
                4.38F,
                0,
                List.of("Pulp")
        );
        Food apple = new Fruit(
                "Apple",
                timeNow.minusMonths(3),
                timeNow.plusMonths(12),
                2.40F,
                0,
                List.of("Pulp")
        );
        Food mandarin = new Fruit(
                "Apple",
                timeNow.minusMonths(12),
                timeNow.plusMonths(12),
                3.50F,
                0,
                List.of("Pulp")
        );
        shop.add(banana);
        shop.add(apple);
        warehouse.add(mandarin);

        controlQuality.resort();

        assertThat(thrash.findAll()).isNotEmpty()
                .hasSize(1)
                .containsExactly(banana);
        assertThat(shop.findAll()).isNotEmpty()
                .hasSize(1)
                .containsExactly(mandarin);
        assertThat(warehouse.findAll()).isNotEmpty()
                .hasSize(1)
                .containsExactly(apple);
    }

}