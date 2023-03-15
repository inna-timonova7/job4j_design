package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    public void whenPairWithNullValueOrKeyProperty() {
        String path = "./data/pair_empty_value_name.properties";
        String path2 = "./data/pair_empty.key_name.properties";
        Config config = new Config(path);
        Config config2 = new Config(path2);
        config.load();
        assertNull(config.value("name"));
        assertNull(config2.value("name"));
    }
}