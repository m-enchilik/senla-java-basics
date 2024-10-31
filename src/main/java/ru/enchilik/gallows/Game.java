package ru.enchilik.gallows;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.enchilik.gallows.Consts.gallows;

public class Game {

    private String word;
    private int hp = gallows.length;

    private Set<Character> enteredChars = new HashSet<>();

    private Set<Character> wordChars;

    private final GameUI gameUI;

    public Game(GameUI gameUI) {
        this.gameUI = gameUI;

    }

    public void play() {
        enterTheWord();
        for (; ; ) {
            char ch = gameUI.enterChar();
            if (isExitCommand(ch)) {
                return;
            }
            enteredChars.add(ch);
            evaluateChar(ch);
            showState();
            if (failed()) {
                gameUI.out("Oooops... ");
                return;
            }
            if (won()) {
                gameUI.out("CONGRATULATIONS!!!");
                return;
            }
        }
    }

    private boolean won() {
        return enteredChars.containsAll(wordChars);
    }

    private boolean failed() {
        return hp <= 0;
    }

    private void showState() {
        showImage();
        showGuessedChars();
        showStatistics();
    }

    private void showStatistics() {
        gameUI.out("HP: " + hp);
    }

    private void showGuessedChars() {
        StringBuilder sb = new StringBuilder();
        for (char ch : word.toCharArray()) {
            sb.append(enteredChars.contains(ch) ? ch : '_');
        }
        gameUI.out(sb.toString());
    }

    private void showImage() {
        if (hp == gallows.length) {
            return;
        }

        gameUI.out(gallows[gallows.length - 1 - hp]);
    }

    private boolean isExitCommand(char ch) {
        return '0' == ch;
    }

    private void evaluateChar(char ch) {
        if (!word.contains(String.valueOf(ch))) {
            hp--;
        }
    }

    private void enterTheWord() {
        word = gameUI.enterTheWord();
        wordChars = word.chars()
                .mapToObj(e->(char)e)
                .collect(Collectors.toSet());
    }
}
