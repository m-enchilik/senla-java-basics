package ru.enchilik.gallows.cli;


import ru.enchilik.gallows.GameUI;

import java.util.Random;
import java.util.Scanner;

import static ru.enchilik.gallows.Consts.dictionary;
import static ru.enchilik.gallows.Consts.validCharacters;

public class CliGameUI implements GameUI {
    private final String wordMustHave = "\nМаленькое правило ввода слова и букв:" +
            "\n1. Слово состоит из кириллицы и не менее 3-х букв. " +
            "\n2. В слове нет символов и цифр." +
            "\n3. Буквы (кириллицу) вводить по одной." +
            "\n4. Для выхода наберите 0 в любом месте игры.";

    private Scanner scanner = new Scanner(System.in);

    @Override
    public char enterChar() {
        while (true) {
            System.out.println("Введите букву кириллицы." +
                    "\nИли наберите 0 для выхода.");
            String input = scanner.nextLine().toLowerCase();
            if (input.length() != 1) {
                System.out.println("Ведено неверное количество символов.");
                continue;
            }
            var ch = input.charAt(0);
            if (isValidInput(ch)) {
                return ch;
            }
            System.out.println("Введена не кириллическая буква.");
        }
    }

    @Override
    public String enterTheWord() {
        System.out.println(wordMustHave);
        Random random = new Random();
        int numOfWord = random.nextInt(dictionary.length);
        String word = dictionary[numOfWord].toLowerCase();
        return word;
    }

    @Override
    public void out(String message) {
        System.out.println(message);
    }

    private boolean isValidInput(char input) {
        return (input == '0' || validCharacters.contains(input));
    }
}
