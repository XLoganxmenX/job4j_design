package ru.job4j.kiss.fool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;

class FoolTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void whenInitOver5Numbers() {
        String userInput = new StringJoiner(System.lineSeparator())
                .add("2")
                .add("4")
                .toString();
        var input = new Scanner(userInput);
        Fool app = new Fool();

        app.init(input);
        String consoleText = outputStream.toString();
        StringJoiner appOutput = new StringJoiner(System.lineSeparator())
                .add("Игра FizzBuzz.")
                .add("1")
                .add("Fizz")
                .add("Buzz\r\n");

        String result = new String(appOutput.toString().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        assertThat(consoleText).isEqualTo(result);
    }

    @Test
    public void whenStartTurnWith3ThenFizz() {
        var input = new Scanner("");
        Fool app = new Fool();

        app.startTurn(input, 3);
        String consoleText = outputStream.toString();

        assertThat(consoleText).isEqualTo("Fizz" + System.lineSeparator());
    }

    @Test
    public void whenStartTurnWith5ThenBuzz() {
        var input = new Scanner("");
        Fool app = new Fool();

        app.startTurn(input, 5);
        String consoleText = outputStream.toString();

        assertThat(consoleText).isEqualTo("Buzz" + System.lineSeparator());
    }

    @Test
    public void whenStartTurnWith15ThenFizzBuzz() {
        var input = new Scanner("");
        Fool app = new Fool();

        app.startTurn(input, 15);
        String consoleText = outputStream.toString();

        assertThat(consoleText).isEqualTo("FizzBuzz" + System.lineSeparator());
    }

    @Test
    public void whenStartTurnWith1Then1() {
        var input = new Scanner("");
        Fool app = new Fool();

        app.startTurn(input, 1);
        String consoleText = outputStream.toString();

        assertThat(consoleText).isEqualTo(1 + System.lineSeparator());
    }
}