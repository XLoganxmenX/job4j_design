package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void whenGetNumberOfVerticesV0ThenTrue() {
        Box box = new Box(0, 10);
        int res = box.getNumberOfVertices();
        assertThat(res).isNotNull()
                .isLessThan(1)
                .isGreaterThan(-1)
                .isEqualTo(0);
    }

    @Test
    void whenGetNumberOfVerticesVNegative1ThenTrue() {
        Box box = new Box(-1, 10);
        int res = box.getNumberOfVertices();
        assertThat(res).isNotNull()
                .isNotZero()
                .isNegative()
                .isOdd()
                .isLessThan(0)
                .isGreaterThan(-2)
                .isEqualTo(-1);
    }

    @Test
    void whenVortexIsExistThenTrue() {
        Box box = new Box(0, 1);
        boolean res = box.isExist();
        assertThat(res).isTrue();
    }

    @Test
    void whenVortexIsNotExistThenFalse() {
        Box box = new Box(-1, 1);
        boolean res = box.isExist();
        assertThat(res).isFalse();
    }

    @Test
    void whenVortex0Edge10ThenGetAreaIs1256dot637() {
        Box box = new Box(0, 10);
        double res = box.getArea();
        assertThat(res).isNotNull()
                .isNotZero()
                .isPositive()
                .isEqualTo(1256.637d, withPrecision(0.005d))
                .isCloseTo(1256.637d, withPrecision(0.001d))
                .isCloseTo(1256.637d, Percentage.withPercentage(1.0d));
    }

    @Test
    void whenVortex4Edge10ThenGetAreaIs173dot205() {
        Box box = new Box(4, 10);
        double res = box.getArea();
        assertThat(res).isNotNull()
                .isNotZero()
                .isPositive()
                .isEqualTo(173.205d, withPrecision(0.005d))
                .isCloseTo(173.205d, withPrecision(0.001d))
                .isCloseTo(173.205d, Percentage.withPercentage(1.0d));
    }

    @Test
    void whenVortex8Edge10ThenGetAreaIs600() {
        Box box = new Box(8, 10);
        double res = box.getArea();
        assertThat(res).isNotNull()
                .isNotZero()
                .isPositive()
                .isEqualTo(600d, withPrecision(0.005d))
                .isCloseTo(600d, withPrecision(0.001d))
                .isCloseTo(600d, Percentage.withPercentage(1.0d));
    }

    @Test
    void checkInitWhenEdgeNegative1ThenVortexNegative1() {
        Box box = new Box(0, -1);
        int res = box.getNumberOfVertices();
        assertThat(res).isNotNull()
                .isNotZero()
                .isNegative()
                .isOdd()
                .isLessThan(0)
                .isGreaterThan(-2)
                .isEqualTo(-1);
    }

    @Test
    void checkInitWhenTypeUnknownThenVortexNegative1() {
        Box box = new Box(1, 10);
        int res = box.getNumberOfVertices();
        assertThat(res).isNotNull()
                .isNotZero()
                .isNegative()
                .isOdd()
                .isLessThan(0)
                .isGreaterThan(-2)
                .isEqualTo(-1);
    }
}