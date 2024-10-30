package ru.enchilik.gallows;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.enchilik.gallows.Consts.gallows;

public class Game {
    // TODO: extract all "System.out.println" to the UI interface and it's CLI implementation

    private String word;
    private int hp = gallows.length;

    private Set<Character> enteredChars = new HashSet<>();

    private Set<Character> wordChars;

    private final GameInput gameInput;

    public Game(GameInput gameInput) {
        this.gameInput = gameInput;

    }

    public void play() {
        enterTheWord();
        for (; ; ) {
            char ch = gameInput.enterChar();
            if (isExitCommand(ch)) {
                return;
            }
            enteredChars.add(ch);
            evaluateChar(ch);
            showState();
            if (failed()) {
                System.out.println("Oooops... ");
                return;
            }
            if (won()) {
                System.out.println("CONGRATULATIONS!!!");
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
        System.out.println("HP: " + hp);
    }

    private void showGuessedChars() {
        for (char ch : word.toCharArray()) {
            System.out.print(enteredChars.contains(ch) ? ch : '_');
        }
        System.out.println();
    }

    private void showImage() {
        if (hp == gallows.length) {
            return;
        }

        System.out.println(gallows[gallows.length - 1 - hp]);
        // todo: implement this
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
        word = gameInput.enterTheWord();
        wordChars = word.chars()
                .mapToObj(e->(char)e)
                .collect(Collectors.toSet());
    }
}
