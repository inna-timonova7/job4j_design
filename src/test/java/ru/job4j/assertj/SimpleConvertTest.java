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
        String[] array = simpleConvert.toArray("first", "second", "third", "fourth", "fifth");
        List<String> list = simpleConvert.toList(array);
        assertThat(list).hasSize(5)
                .contains("first", "fourth")
                .containsExactly("first", "second", "third", "fourth", "fifth")
                .containsAnyOf("tenth", "third", "sixth")
                .endsWith("fifth")
                .first().isEqualTo("first");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("one", "two", "three", "four", "five");
        Set<String> set = simpleConvert.toSet(array);
        assertThat(set).isNotNull()
                /*все элементы выполняют условие*/
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(9);
                })
                .containsExactlyInAnyOrder("two", "four", "one", "five", "three")
                .anySatisfy(e -> {
                    assertThat(e).isLessThan("six");
                    assertThat(e).isEqualTo("four");
                });
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("one", "two", "three", "four", "five");
        Map<String, Integer> map = simpleConvert.toMap(array);
        assertThat(map).isNotEmpty()
                .hasSize(5)
                .containsKeys("three", "two", "five")
                .containsValues(0, 1, 2, 3, 4)
                .doesNotContainKeys("seven", "nine")
                .doesNotContainValue(6)
                .containsEntry("one", 0);
    }
}