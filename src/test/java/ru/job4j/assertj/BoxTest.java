package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .contains("Tetra")
                .isNotEmpty();
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .doesNotContain("Sphere")
                .isNotEmpty();
    }

    @Test
    void whenFourVertexes() {
        Box box = new Box(4, 4);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(4)
                .isPositive()
                .isEven()
                .isGreaterThan(3)
                .isLessThan(5);
    }

    @Test
    void whenEightVertexes() {
        Box box = new Box(8, 12);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(8)
                .isPositive()
                .isNotZero()
                .isLessThan(9);
    }

    @Test
    void whenExist() {
        Box box = new Box(4, 4);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDoesNotExist() {
        Box box = new Box(3, 7);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void tetrahedronArea() {
        Box box = new Box(4, 4);
        double area = box.getArea();
        assertThat(area).isNotZero()
                .isLessThan(30)
                .isEqualTo(27.712812921102035);
    }

    @Test
    void cubeArea() {
        Box box = new Box(8, 6);
        double area = box.getArea();
        assertThat(area).isNotZero()
                .isGreaterThan(200)
                .isEqualTo(216.0);
    }
}