package ru.job4j.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

class NonCollisionMapTest {

    private final SimpleMap<Integer, String> map = new NonCollisionMap<>();

    @BeforeEach
    void setUp() {
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
    }

    @Test
    void checkSimpleIterator() {
        assertThat(map).hasSize(4).contains(1, 2, 3, 4);
    }

    @Test
    void whenCheckGet() {
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(4);
        assertThat(map.get(5)).isNull();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckPut() {
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map).hasSize(5);
        assertThat(map.put(8, "8")).isFalse();
        assertThat(map).hasSize(5);
        assertThat(map.put(1, "10")).isFalse();
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemove() {
        assertThat(map.remove(2)).isTrue();
        assertThat(map).hasSize(3);
        assertThat(map.remove(2)).isFalse();
        assertThat(map).hasSize(3);
        assertThat(map.remove(5)).isFalse();
        assertThat(map).hasSize(3);
    }

    @Test
    void whenCheckIterator() {
        map.remove(2);
        map.remove(3);
        map.put(null, "0000");
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isNull();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenConcurrentIteratorAdd() {
        Iterator<Integer> iterator = map.iterator();
        map.put(0, "0");
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenConcurrentIteratorRemove() {
        Iterator<Integer> iterator = map.iterator();
        map.remove(1);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenNotConcurrentIteratorGet() {
        Iterator<Integer> iterator = map.iterator();
        map.get(1);
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenMapExpand() {
        map.put(null, "0000");
        assertThat(map.put(15, "15")).isTrue();
        assertThat(map).hasSize(6);
        assertThat(map.put(8, "8")).isTrue();
        assertThat(map.put(16, "16")).isFalse();
        assertThat(map.get(4)).isEqualTo("4");
        assertThat(map.get(8)).isEqualTo("8");
        assertThat(map.get(15)).isEqualTo("15");
        assertThat(map).hasSize(7).contains(null, 1, 2, 3, 4, 8, 15);
    }

    @Test
    void whenCheckPutKeyNull() {
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckGetKeyNull() {
        map.put(null, "0000");
        assertThat(map.get(null)).isEqualTo("0000");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemoveKeyNull() {
        map.put(null, "0000");
        assertThat(map.remove(null)).isTrue();
        assertThat(map).hasSize(4);
    }


    @Test
    void whenCheckPutZeroAndNull() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.put(0, "0")).isFalse();
    }

    @Test
    void whenCheckPutNullAndZero() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.put(null, "0000")).isFalse();
    }

    @Test
    void whenCheckGetZeroAndNull() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.get(0)).isNull();
    }

    @Test
    void whenCheckGetNullAndZero() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.get(null)).isNull();
    }
    /////////////////////////////////////////////////
    @Test
    void whenPutNegativeKey() {
        assertThat(map.put(-1, "-1")).isTrue();
        assertThat(map).hasSize(5);
    }

    @Test
    void whenGetNegativeKey() {
        map.put(-1, "-1");
        assertThat(map.get(-1)).isEqualTo("-1");
    }

    @Test
    void whenPutDoubleKeyAndValue() {
        SimpleMap<Double, Double> map = new NonCollisionMap<>();
        assertThat(map.put(1.756d, 1.756d)).isTrue();
        assertThat(map).hasSize(1);
    }

    @Test
    void whenGetDoubleKeyAndValue() {
        SimpleMap<Double, Double> map = new NonCollisionMap<>();
        map.put(1.756d, 1.756d);
        assertThat(map.get(1.756d)).isEqualTo(1.756d);
    }

    @Test
    void whenPutObjectKeyAndValue() {
        SimpleMap<Object, Object> map = new NonCollisionMap<>();
        Object key = new Object();
        Object value = new Object();
        assertThat(map.put(key, value)).isTrue();
        assertThat(map).hasSize(1);
    }

    @Test
    void whenGetObjectKey() {
        SimpleMap<Object, Object> map = new NonCollisionMap<>();
        Object key = new Object();
        Object value = new Object();
        map.put(key, value);
        assertThat(map.get(key)).isEqualTo(value);
    }

    @Test
    void whenRemoveFromEmptyMap() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        assertThat(map.remove(0)).isFalse();
    }

    @Test
    void whenPutEqualsObjects() {
        SimpleMap<User, String> map = new NonCollisionMap<>();
        User user1 = new User("Vasiliy", 2, new GregorianCalendar(1985, Calendar.JANUARY, 10));
        User user2 = new User("Vasiliy", 2, new GregorianCalendar(1985, Calendar.JANUARY, 10));
        map.put(user1, "Pswd1234");
        assertThat(map.put(user2, "1111111")).isFalse();
        assertThat(map).hasSize(1);
    }

    @Test
    void whenGetEqualsObjects() {
        SimpleMap<User, String> map = new NonCollisionMap<>();
        User user1 = new User("Vasiliy", 2, new GregorianCalendar(1985, Calendar.JANUARY, 10));
        User user2 = new User("Vasiliy", 2, new GregorianCalendar(1985, Calendar.JANUARY, 10));
        map.put(user1, "Pswd1234");
        assertThat(map.get(user2)).isEqualTo("Pswd1234");
    }

    @Test
    void whenRemoveEqualsObjects() {
        SimpleMap<User, String> map = new NonCollisionMap<>();
        User user1 = new User("Vasiliy", 2, new GregorianCalendar(1985, Calendar.JANUARY, 10));
        User user2 = new User("Vasiliy", 2, new GregorianCalendar(1985, Calendar.JANUARY, 10));
        map.put(user1, "Pswd1234");
        assertThat(map.remove(user2)).isTrue();
        assertThat(map).isEmpty();
    }

    @Test
    void whenIteratorNextFromEmptyMap() {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        Iterator<Integer> iterator = map.iterator();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenIteratorFromObjectMap() {
        SimpleMap<User, String> map = new NonCollisionMap<>();
        User user1 = new User("Vasiliy", 2, new GregorianCalendar(1985, Calendar.JANUARY, 10));
        User user2 = new User("Petr", 3, new GregorianCalendar(1980, Calendar.APRIL, 1));
        map.put(user1, "Pswd1234");
        map.put(user2, "11111111");
        Iterator<User> iterator = map.iterator();
        assertThat(iterator.next()).isEqualTo(user1);
        assertThat(iterator.next()).isEqualTo(user2);
    }
}