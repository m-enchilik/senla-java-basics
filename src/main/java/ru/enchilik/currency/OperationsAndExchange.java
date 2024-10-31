package ru.enchilik.currency;

import java.util.Scanner;

public class OperationsAndExchange {
    private final String exitText = "Чтобы выйти из приложения, наберите любое отрицательное число в любое время.";
    private final String textSetCurrentRate = "Установите текущий курс валют: ";
    private String temp;
    private Currency[] currencies;

    private WorkingWithConsole workingWithConsole;
    private Scanner scanner = new Scanner(System.in);

    public OperationsAndExchange() {
        currencies = Currency.values();
        workingWithConsole = new WorkingWithConsole(this);
    }

    public void setCurrenciesRate() {
        System.out.println(exitText
                    + "\n" + textSetCurrentRate);
        for (Currency cur : currencies) {
            setCurrencyRate(cur.ordinal());
        }
    }

    public double setCurrencyRate(int currencyCode) {
        System.out.print(currencies[currencyCode] + " - ");
        temp = scanner.nextLine();
        if (!workingWithConsole.isDouble(temp)) {
            System.out.println("Курс не установлен.");
            currencies[currencyCode].setVal(0);
            return 0;
        }

        double newValue = Double.parseDouble(temp);

        if (newValue < 0) {
            System.exit(-1);
        }

        currencies[currencyCode].setVal(newValue);
        return newValue;
    }

    public void exchangeCurrency(int innerCodeCurrency, double amount) {
        for (Currency currency : Currency.values()) {
            if (currency.ordinal() == innerCodeCurrency) continue;
            if (currency.getVal() == 0) {
                System.out.println(currency.name() + ": курс не установлен.");
                continue;
            }
            double money = (amount * currencies[innerCodeCurrency].getVal()) / currency.getVal();
            System.out.println(String.format("%s: %.2f", currency.name(), money));
        }
    }

    public Currency[] getCurrencies() {
        return currencies;
    }
}