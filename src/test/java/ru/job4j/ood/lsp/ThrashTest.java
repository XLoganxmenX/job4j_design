package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;

class ThrashTest {

    @Test
    void whenAddFood() {
        Store thrash = new Thrash();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        thrash.add(banana);

        assertThat(thrash.findAll()).isNotEmpty()
                .hasSize(1)
                .containsOnly(banana);
    }

    @Test
    void whenFindAll() {
        Store thrash = new Thrash();
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

        thrash.add(banana);
        thrash.add(apple);
        thrash.add(mandarin);

        assertThat(thrash.findAll()).isNotEmpty()
                .hasSize(3)
                .contains(banana, apple, mandarin);
    }

    @Test
    void whenFindById() {
        Store thrash = new Thrash();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        thrash.add(banana);

        assertThat(thrash.findById(1)).isEqualTo(banana);
    }

    @Test
    void whenDeleteOneFood() {
        Store thrash = new Thrash();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        thrash.add(banana);

        assertThat(thrash.delete(1)).isTrue();
        assertThat(thrash.findAll()).isEmpty();
    }

    @Test
    void whenDeleteTwoFood() {
        Store thrash = new Thrash();
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
        thrash.add(banana);
        thrash.add(apple);
        thrash.add(mandarin);

        thrash.delete(1);
        thrash.delete(2);

        assertThat(thrash.findAll()).hasSize(1)
                .containsExactly(mandarin);
    }

    @Test
    void whenDeleteThenGetComponents() {
        Thrash thrash = new Thrash();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp", "Juice")
        );

        thrash.add(banana);
        thrash.delete(1);

        assertThat(thrash.getComponents()).containsExactly("Pulp", "Juice");
    }

    @Test
    void whenAddFoodThenIdAssign() {
        Store thrash = new Thrash();
        Food banana = new Fruit(
                "Banana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4.38F,
                0,
                List.of("Pulp")
        );

        thrash.add(banana);

        assertThat(banana.getId()).isEqualTo(1);
    }
}