package ru.enchilik.gallows;

import ru.enchilik.gallows.cli.CliGameInput;

public class App {

    public static void main(String[] args) {
        new Game(new CliGameInput()).play();
    }
}
