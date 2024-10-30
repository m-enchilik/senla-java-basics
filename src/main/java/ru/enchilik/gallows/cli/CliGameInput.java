package ru.enchilik.gallows.cli;


import ru.enchilik.gallows.GameInput;

import java.util.Scanner;

import static ru.enchilik.gallows.Consts.validCharacters;

public class CliGameInput implements GameInput {
    private final String wordMustHave = "Данная игра для двух игроков, один игрок загадывает слово, другой его отгадывает." +
            "\nМаленькое правило ввода слова и букв:" +
            "\n1. Слово должно состоять из кириллицы и не менее 3-х букв. " +
            "\n2. Не должно быть символов и цифр." +
            "\n3. Буквы вводить по одной и по вышеуказанным правилам." +
            "\n4. Для выхода наберите 0 в любом месте игры.";
    private final String firstPlayersMove = "Загадайте слово (набрав его на клавиатуре):";

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
        while (true) {
            System.out.println(wordMustHave);
            System.out.println(firstPlayersMove);
            String word = scanner.nextLine().toLowerCase();
            if (!word.isEmpty() && '0' == word.charAt(0)) {
                System.exit(0);
            }
            if (wordCheck(word)) {
                return word;
            }
            System.out.println("Что-то пошло не так.");
        }
    }

    private boolean wordCheck(String word) {
        if (word.length() < 3){
            return false;
        }
        String regex = "[а-я]*";
        if (word.matches(regex)) {
            return true;
        }
        return false;
    }

    private boolean isValidInput(char input) {
        return (input == '0' || validCharacters.contains(input));
    }
}
