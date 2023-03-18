package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    public void whenPairWithoutComments() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Inna Timonova");
    }

    @Test
    public void whenPairWithComments() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Inna Timonova");
    }

    @Test
    public void whenPairWithNullValueProperty() {
        String path = "./data/pair_empty_value_name.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Mistake in the key=value pattern");
    }

    @Test
    public void whenPairWithNullKeyProperty() {
        String path = "./data/pair_empty.key_name.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Mistake in the key=value pattern");
    }
}