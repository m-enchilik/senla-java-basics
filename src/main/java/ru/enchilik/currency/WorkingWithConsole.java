package ru.enchilik.currency;

import java.util.Scanner;

public class WorkingWithConsole {
    private final String textCurrencyCode = "\nКоды валют: " + "\n"
            + "USD - 0, EUR - 1, RUB - 2, CNY - 3, KZT - 4";
    private String incorrectData = "\nВведенные данные неверны. Начните сначала.";
    private String temp;
    private Scanner scanner;
    private Currency[] currencies;
    private OperationsAndExchange operationsAndExchange;

    public WorkingWithConsole(OperationsAndExchange operationsAndExchange) {
        scanner = new Scanner(System.in);
        this.operationsAndExchange = operationsAndExchange;
        currencies = operationsAndExchange.getCurrencies();
    }

    public void inputToExchange() {
        while (true) {
            System.out.println(textCurrencyCode);

            Integer inner = currencyToExchange();
            if (inner == null) continue;

            Double amount = enterTheSum();
            if (amount == null) continue;

            operationsAndExchange.exchangeCurrency(inner, amount);
        }
    }

    private Double enterTheSum() {
        System.out.print("\nВведите сумму, которую хотите обменять: ");
        temp = scanner.nextLine();
        if (!isDouble(temp)) {
            System.out.println(incorrectData);
            return null;
        }
        double amount = Double.parseDouble(temp);
        isExit(amount);
        if (amount < 0.01) {
            System.out.println("Недостаточно денег для операции.");
            return null;
        }
        return amount;
    }

    private Integer currencyToExchange() {
        System.out.print("\nВведите код вашей валюты: ");
        temp = scanner.nextLine();
        if (!isInteger(temp)) {
            System.out.println(incorrectData);
            return null;
        }
        int inner = Integer.parseInt(temp);
        isExit(inner);
        if (isIncorrectCodeCurrency(inner)) return null;
        if (currencies[inner].getVal() == 0) {
            System.out.println("Курс для данной валюты не установлен.");
            return null;
        }
        return inner;
    }

    private boolean isIncorrectCodeCurrency(int inner) {
        if (inner > currencies.length - 1) {
            System.out.println(incorrectData);
            return true;
        }
        return false;
    }

    public boolean isDouble(String temp) {
        try {
            Double.parseDouble(temp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isInteger(String temp) {
        try {
            Integer.parseInt(temp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void isExit(double value) {
        if (value < 0) System.exit(-1);
    }
}
