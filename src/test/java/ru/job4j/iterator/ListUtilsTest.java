package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfThenRemove() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeIf(input, e -> e > 2);
        assertThat(input).hasSize(2).containsSequence(1, 2);
    }

    @Test
    void whenRemoveIfThenNoRemove() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeIf(input, e -> e > 5);
        assertThat(input).hasSize(4).containsSequence(1, 2, 3, 4);
    }

    @Test
    void whenRemoveIfThenAllRemove() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeIf(input, e -> e > 0);
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemoveIfOnEmptyList() {
        input = new ArrayList<>();
        ListUtils.removeIf(input, e -> e > 2);
        assertThat(input).isEmpty();
    }

    @Test
    void whenReplaceIf() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.replaceIf(input, e -> e % 2 == 0, 0);
        assertThat(input).hasSize(4).containsSequence(1, 0, 3, 0);
    }

    @Test
    void whenReplaceIfThenNoReplace() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.replaceIf(input, e -> e > 5, 0);
        assertThat(input).hasSize(4).containsSequence(1, 2, 3, 4);
    }

    @Test
    void whenReplaceIfThenAllReplace() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.replaceIf(input, e -> e > 0, 0);
        assertThat(input).hasSize(4).containsSequence(0, 0, 0, 0);
    }

    @Test
    void whenReplaceIfOnEmptyList() {
        input = new ArrayList<>();
        ListUtils.replaceIf(input, e -> e > 0, 0);
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> removeList = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.removeAll(input, removeList);
        assertThat(input).hasSize(2).containsSequence(3, 4);
    }

    @Test
    void whenRemoveAllThenAllRemove() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> removeList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeAll(input, removeList);
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemoveAllThenNoRemove() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> removeList = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
        ListUtils.removeAll(input, removeList);
        assertThat(input).hasSize(4).containsSequence(1, 2, 3, 4);
    }

    @Test
    void whenRemoveAllOnEmptyList() {
        input = new ArrayList<>();
        ArrayList<Integer> removeList = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.removeAll(input, removeList);
        assertThat(input).isEmpty();
    }

}