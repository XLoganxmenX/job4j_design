package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one", "two", "three");
        assertThat(list).isNotNull()
                .hasSize(3)
                .isNotEmpty()
                .contains("one")
                .containsAnyOf("one", "two", "three")
                .containsExactly("one", "two", "three");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("one", "one", "two", "two", "three", "three");
        assertThat(set).isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .contains("one")
                .containsExactly("one", "two", "three");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "two", "three");
        assertThat(map).isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .containsKeys("one", "two", "three")
                .containsValues(0, 1, 2)
                .doesNotContainValue(-1)
                .doesNotContainValue(3)
                .containsEntry("two", 1);
    }
}