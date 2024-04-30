package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    void whenAddFood() {
        Store shop = new Shop();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        shop.add(banana);

        assertThat(shop.findAll()).isNotEmpty()
                .hasSize(1)
                .containsOnly(banana);
    }

    @Test
    void whenFindAll() {
        Store shop = new Shop();
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

        shop.add(banana);
        shop.add(apple);
        shop.add(mandarin);

        assertThat(shop.findAll()).isNotEmpty()
                .hasSize(3)
                .contains(banana, apple, mandarin);
    }

    @Test
    void whenFindById() {
        Store shop = new Shop();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        shop.add(banana);

        assertThat(shop.findById(1)).isEqualTo(banana);
    }

    @Test
    void whenDeleteOneFood() {
        Store shop = new Shop();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        shop.add(banana);

        assertThat(shop.delete(1)).isTrue();
        assertThat(shop.findAll()).isEmpty();
    }

    @Test
    void whenDeleteTwoFood() {
        Store shop = new Shop();
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
        shop.add(banana);
        shop.add(apple);
        shop.add(mandarin);

        shop.delete(1);
        shop.delete(2);

        assertThat(shop.findAll()).hasSize(1)
                .containsExactly(mandarin);
    }

    @Test
    void whenAddFoodThenIdAssign() {
        Store shop = new Shop();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        shop.add(banana);

        assertThat(banana.getId()).isEqualTo(1);
    }

    @Test
    void whenAcceptByExpiryDate() {
        Store shop = new Shop();
        assertThat(shop.isAcceptByExpiryDate(0.25d)).isTrue();
        assertThat(shop.isAcceptByExpiryDate(0.70d)).isTrue();
        assertThat(shop.isAcceptByExpiryDate(0.90d)).isTrue();
    }

    @Test
    void whenNotAcceptByExpiryDate() {
        Store shop = new Shop();
        assertThat(shop.isAcceptByExpiryDate(0.20d)).isFalse();
        assertThat(shop.isAcceptByExpiryDate(1.20d)).isFalse();
    }
}