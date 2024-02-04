package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "Data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Dmitriy Avdoshin");
    }

    @Test
    void whenPairsWithCommentAndSkips() {
        String path = "Data/pair_with_comment_skips.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("id")).isEqualTo("100");
        assertThat(config.value("login")).isEqualTo("admin");
        assertThat(config.value("password")).isEqualTo("123456");
    }

    @Test
    void whenNoKeyAndNoValue() {
        String path = "Data/when_NoKey_NoValue.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeyAndNoValue() {
        String path = "Data/when_Key_NoValue.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoKeyAndValue() {
        String path = "Data/when_Key_NoValue.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMoreThenOneEquals() {
        String path = "Data/when_More_Then_One_Equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value=1");
        assertThat(config.value("user")).isEqualTo("admin=");
    }

    @Test
    void whenValidConfig() {
        String path = "Data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"))
                .isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("hibernate.connection.url"))
                .isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
        assertThat(config.value("hibernate.connection.driver_class"))
                .isEqualTo("org.postgresql.Driver");
        assertThat(config.value("hibernate.connection.username"))
                .isEqualTo("postgres");
        assertThat(config.value("hibernate.connection.password"))
                .isEqualTo("password");
    }

}