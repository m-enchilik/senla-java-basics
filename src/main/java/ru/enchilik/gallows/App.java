package ru.enchilik.gallows;

import ru.enchilik.gallows.cli.CliGameUI;

public class App {

    public static void main(String[] args) {
        new Game(new CliGameUI()).play();
    }
}
