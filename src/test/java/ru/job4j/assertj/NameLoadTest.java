package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkValidateWithEqualSign() {
        NameLoad nameLoad = new NameLoad();
        String word = "Not:Equal";
        String errorWord = "=";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(word, errorWord);
    }

    @Test
    void checkValidateWithoutKey() {
        NameLoad nameLoad = new NameLoad();
        String word = "=value";
        String errorWord = "key";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(word, errorWord);
    }

    @Test
    void checkValidateWithoutValue() {
        NameLoad nameLoad = new NameLoad();
        String word = "Key=";
        String errorWord = "value";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(word, errorWord);
    }

    @Test
    void whenParseEmpty() {
        NameLoad nameLoad = new NameLoad();
        String errorWord = "empty";
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(errorWord);
    }
}