package ru.job4j.template;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class GeneratorTest {

    @Test
    public void whenGenerateCorrectPhrase(){
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name","Dmitriy",
                "subject", "you"
        );
        String expected = "I am a Dmitriy, Who are you?";

        assertThat(generator.produce(template, args)).isEqualTo(expected);
    }

    @Test
    public void whenTemplateHasOneKey(){
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}";
        Map<String, String> args = Map.of(
                "name","Dmitriy"
        );
        String expected = "I am a ${name}";

        assertThat(generator.produce(template, args)).isEqualTo(expected);
    }


    @Test
    public void whenValuesHasMoreThenOneWord(){
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name","Ivanov Petr Stepanovich",
                "subject", "you and I"
        );
        String expected = "I am a Ivanov Petr Stepanovich, Who are you and I?";

        assertThat(generator.produce(template, args)).isEqualTo(expected);
    }

    @Test
    public void whenEmptyTemplateThenException(){
        Generator generator = new PhraseGenerator();
        String template = "";
        Map<String, String> args = Map.of(
                "name","Dmitriy",
                "subject", "you"
        );

        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Template is empty");
    }

    @Test
    public void whenEmptyArgsThenException(){
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();

        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Args is empty");
    }

    @Test
    public void whenArgHasWrongKeysThenException(){
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}? Am I ${role}?";
        Map<String, String> args = Map.of(
                "name","Dmitriy",
                "subject", "you"
        );

        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Key ${role} not found in args");
    }

    @Test
    public void whenArgHasMoreKeysThenTemplate(){
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}";
        Map<String, String> args = Map.of(
                "name","Dmitriy",
                "subject", "you",
                "role", "author"
        );

        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Key ${name}, ${role} not found in template");
    }

}