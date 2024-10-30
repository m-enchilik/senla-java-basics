package ru.enchilik.password;

import java.util.List;
import java.util.Scanner;

public class CreatePassword {

    private Generator generator = new Generator();
    private Scanner scanner = new Scanner(System.in);
    private int passLength = 0;
    private static final List<String> length = List.of("8", "9", "10", "11", "12");

    public CreatePassword() {
    }

    public void start () {
        while (passLength < 8 || passLength > 12) {
            System.out.println("Введите желаемую длину пароля в диапазоне от 8 до 12 символов" +
                    " или 0 для выхода из приложения");
            String temp = scanner.nextLine();
            if(isExit(temp)) return;
            if (!length.contains(temp)) {
                System.out.println("Неверно введены данные.");
                continue;
            }
            passLength = Integer.parseInt(temp);
        }

        String password = generator.createPass(passLength);

        System.out.println("Никому не показывайте, и никому не сообщайте ваш пароль: " + password);
    }

    private boolean isExit(String temp) {
        return ("0".equals(temp));
    }
}

