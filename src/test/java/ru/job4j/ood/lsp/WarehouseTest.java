package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {
    @Test
    void whenAddFood() {
        Store warehouse = new Warehouse();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        warehouse.add(banana);

        assertThat(warehouse.findAll()).isNotEmpty()
                .hasSize(1)
                .containsOnly(banana);
    }

    @Test
    void whenFindAll() {
        Store warehouse = new Warehouse();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );
        Food apple = new Fruit(
                "Apple",
                LocalDateTime.now(),
                LocalDateTime.now(),
                2.40F,
                0,
                List.of("Pulp")
        );
        Food mandarin = new Fruit(
                "Apple",
                LocalDateTime.now(),
                LocalDateTime.now(),
                3.50F,
                0,
                List.of("Pulp")
        );

        warehouse.add(banana);
        warehouse.add(apple);
        warehouse.add(mandarin);

        assertThat(warehouse.findAll()).isNotEmpty()
                .hasSize(3)
                .contains(banana, apple, mandarin);
    }

    @Test
    void whenFindById() {
        Store warehouse = new Warehouse();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        warehouse.add(banana);

        assertThat(warehouse.findById(1)).isEqualTo(banana);
    }

    @Test
    void whenDeleteOneFood() {
        Store warehouse = new Warehouse();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        warehouse.add(banana);

        assertThat(warehouse.delete(1)).isTrue();
        assertThat(warehouse.findAll()).isEmpty();
    }

    @Test
    void whenDeleteTwoFood() {
        Store warehouse = new Warehouse();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );
        Food apple = new Fruit(
                "Apple",
                LocalDateTime.now(),
                LocalDateTime.now(),
                2.40F,
                0,
                List.of("Pulp")
        );
        Food mandarin = new Fruit(
                "Apple",
                LocalDateTime.now(),
                LocalDateTime.now(),
                3.50F,
                0,
                List.of("Pulp")
        );
        warehouse.add(banana);
        warehouse.add(apple);
        warehouse.add(mandarin);

        warehouse.delete(1);
        warehouse.delete(2);

        assertThat(warehouse.findAll()).hasSize(1)
                .containsExactly(mandarin);
    }

    @Test
    void whenAddFoodThenIdAssign() {
        Store warehouse = new Warehouse();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        warehouse.add(banana);

        assertThat(banana.getId()).isEqualTo(1);
    }

    @Test
    void whenAcceptByExpiryDate() {
        Store warehouse = new Warehouse();
        assertThat(warehouse.isAcceptByExpiryDate(0.00d)).isTrue();
        assertThat(warehouse.isAcceptByExpiryDate(0.10)).isTrue();
        assertThat(warehouse.isAcceptByExpiryDate(0.24d)).isTrue();
    }

    @Test
    void whenNotAcceptByExpiryDate() {
        Store warehouse = new Warehouse();
        assertThat(warehouse.isAcceptByExpiryDate(0.25d)).isFalse();
        assertThat(warehouse.isAcceptByExpiryDate(0.50d)).isFalse();
        assertThat(warehouse.isAcceptByExpiryDate(1.20d)).isFalse();
    }
}