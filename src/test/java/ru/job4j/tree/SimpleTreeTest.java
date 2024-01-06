package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void whenAddNotExistNode() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 2)).isTrue();
        assertThat(tree.findBy(2)).isPresent();
    }

    @Test
    void whenAddNull() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, null)).isTrue();
    }

    @Test
    void whenTreeOfObjectsAndAddObject() {
        Object parentObj = new Object();
        Object childrenObj = new Object();
        Tree<Object> tree = new SimpleTree<>(parentObj);
        assertThat(tree.add(parentObj, childrenObj)).isTrue();
    }

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenParentNotExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(10, 1)).isFalse();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenParentEqualsChildThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 1)).isFalse();
    }
}